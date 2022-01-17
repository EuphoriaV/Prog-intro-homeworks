package expression.exceptions;

import expression.*;


public class ExpressionParser extends BaseParser implements Parser {
    private Exp curExp;
    private MultyExpression curExpression;

    @Override
    public MultyExpression parse(String string) {
        checkValid(string);
        makeParser(new StringSource(string));
        try {
            return parseExpression(3);
        } catch (StackOverflowError e) {
            throw new InvalidExpressionException("invalid expression");
        }
    }

    private Exp parseConst(boolean negative) {
        StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }
        while (between('0', '9')) {
            sb.append(take1());
        }
        if (Character.isWhitespace(ch)) {
            take();
        }
        if (between('0', '9')) {
            throw new InvalidExpressionException("2 consts in a row");
        }
        try {
            curExpression = new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new InvalidConstException("invalid const");
        }
        return curExp = Exp.CONST;
    }

    private Exp parseExp() {
        if (between('0', '9')) {
            return parseConst(false);
        } else if (between('x', 'z')) {
            curExpression = new Variable(String.valueOf(take()));
            return curExp = Exp.VARIABLE;
        } else {
            switch (take()) {
                case '\0':
                    return null;
                case '+':
                    return curExp = Exp.ADD;
                case '-':
                    return curExp = Exp.SUB;
                case '*':
                    return curExp = Exp.MUL;
                case '/':
                    return curExp = Exp.DIV;
                case 'm':
                    if (take('i')) {
                        if (take('n')) {
                            return curExp = Exp.MIN;
                        }
                    } else if (take('a')) {
                        if (take('x')) {
                            return curExp = Exp.MAX;
                        }
                    }
                case 't':
                    if (take('0')) {
                        return curExp = Exp.T0;
                    }
                case 'l':
                    if (take('0')) {
                        return curExp = Exp.L0;
                    }
                case '(':
                    return curExp = Exp.LEFT;
                case ')':
                    return curExp = Exp.RIGHT;
            }
            throw new InvalidExpressionException("invalid expression");
        }
    }

    private MultyExpression parseExpression(int priority) {
        if (priority == 0) {
            return parseLowPriority();
        } else {
            MultyExpression res = parseExpression(priority - 1);
            while (inPriority(priority)) {
                Exp cur = curExp;
                MultyExpression second = parseExpression(priority - 1);
                switch (cur) {
                    case ADD:
                        res = new CheckedAdd(res, second);
                        break;
                    case SUB:
                        res = new CheckedSubtract(res, second);
                        break;
                    case MUL:
                        res = new CheckedMultiply(res, second);
                        break;
                    case DIV:
                        res = new CheckedDivide(res, second);
                        break;
                    case MIN:
                        res = new Min(res, second);
                        break;
                    case MAX:
                        res = new Max(res, second);
                        break;
                    default:
                        throw new InvalidOperatorException("invalid operator");
                }
            }
            return res;
        }
    }

    private MultyExpression parseLowPriority() {
        parseExp();
        switch (curExp) {
            case CONST:
                MultyExpression cur = curExpression;
                parseExp();
                return cur;
            case VARIABLE:
                MultyExpression curr = curExpression;
                parseExp();
                return curr;
            case SUB:
                if (between('0', '9')) {
                    parseConst(true);
                    cur = curExpression;
                    parseExp();
                    return cur;
                }
                return new CheckedNegate(parseLowPriority());
            case L0:
                return new l0(parseLowPriority());
            case T0:
                return new t0(parseLowPriority());
            case LEFT:
                cur = parseExpression(3);
                parseExp();
                return cur;
        }
        throw new InvalidExpressionException("invalid expression");
    }


    private boolean inPriority(int priority) {
        switch (priority) {
            case 1:
                return curExp == Exp.MUL || curExp == Exp.DIV;
            case 2:
                return curExp == Exp.ADD || curExp == Exp.SUB;
            case 3:
                return curExp == Exp.MAX || curExp == Exp.MIN;
        }
        return false;
    }

    private void checkValid(String string) {
        int left = 0, right = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i + 2 < string.length() && string.charAt(i) == 'l' && string.charAt(i + 2) != '(' && !Character.isWhitespace(string.charAt(i + 2))) {
                throw new InvalidExpressionException("invalid expression");
            }
            if (i + 2 < string.length() && string.charAt(i) == 't' && string.charAt(i + 2) != '(' && !Character.isWhitespace(string.charAt(i + 2))) {
                throw new InvalidExpressionException("invalid expression");
            }
            if (string.charAt(i) == '(') {
                left++;
            }
            if (string.charAt(i) == ')') {
                right++;
            }
            if (i > 0 && string.charAt(i) == 'm' && string.charAt(i - 1) >= '0' && string.charAt(i - 1) <= '9') {
                throw new InvalidExpressionException("invalid expression");
            }
        }
        if (left != right) {
            throw new BracketsException("amount of left brackets doesn't equal to amount of right brackets");
        }
    }
}