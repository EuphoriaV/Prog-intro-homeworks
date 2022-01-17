package expression.parser;

import expression.*;


public class ExpressionParser extends BaseParser implements Parser {
    private Exp curExp;
    private MultyExpression curExpression;

    @Override
    public MultyExpression parse(String string) {
        makeParser(new StringSource(string));
        return parseExpression(3);
    }

    private Exp parseConst(boolean negative) {
        StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }
        while (between('0', '9')) {
            sb.append(take());
        }
        curExpression = new Const(Integer.parseInt(sb.toString()));
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
                case 'l':
                    if (take('0')) {
                        return curExp = Exp.L0;
                    }
                case 't':
                    if (take('0')) {
                        return curExp = Exp.T0;
                    }
                case '(':
                    return curExp = Exp.LEFT;
                case ')':
                    return curExp = Exp.RIGHT;
            }
        }
        return null;
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
                        res = new Add(res, second);
                        break;
                    case SUB:
                        res = new Subtract(res, second);
                        break;
                    case MUL:
                        res = new Multiply(res, second);
                        break;
                    case DIV:
                        res = new Divide(res, second);
                        break;
                    case MIN:
                        res = new Min(res, second);
                        break;
                    case MAX:
                        res = new Max(res, second);
                        break;
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
                return new Negate(parseLowPriority());
            case L0:
                return new l0(parseLowPriority());
            case T0:
                return new t0(parseLowPriority());
            case LEFT:
                cur = parseExpression(3);
                parseExp();
                return cur;
        }
        return null;
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
}
