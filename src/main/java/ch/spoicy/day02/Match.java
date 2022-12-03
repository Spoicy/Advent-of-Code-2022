package ch.spoicy.day02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Match {

    public static final int MOVE_OFFSET_TO_OPP = 23;
    public static final int MOVE_OFFSET_TO_RESULT = 87;
    private final char oppMove;
    private final char selfMove;
    private static final Logger LOG =
            LogManager.getLogger(Match.class);
    private Match(final char oppMove, final char selfMove) {
        this.oppMove = oppMove;
        this.selfMove = selfMove;
    }

    public char getOppMove() {
        return oppMove;
    }

    public char getSelfMove() {
        return selfMove;
    }

    public int getMatchValue() {
        int result = selfMove - oppMove - MOVE_OFFSET_TO_OPP;
        int moveValue = selfMove - MOVE_OFFSET_TO_RESULT;
        switch (result) {
            case 0:
                return moveValue + 3;
            case 1:
            case -2:
                return moveValue + 6;
            case 2:
            case -1:
                return moveValue;
            default:
                throw new RuntimeException("Match value not accounted for.");
        }
    }

    public static Match createWithMove(final char oppMove, final char selfMove) {
        return new Match(oppMove, selfMove);
    }

    public static Match createWithState(final char oppMove, final char state) {
        char selfMove;
        switch (state) {
            case 'X':
                if (oppMove > 'A') {
                    selfMove = (char) (oppMove + MOVE_OFFSET_TO_OPP - 1);
                } else {
                    selfMove = (char) (oppMove + MOVE_OFFSET_TO_OPP + 2);
                }
                break;
            case 'Y':
                selfMove = (char) (oppMove + MOVE_OFFSET_TO_OPP);
                break;
            case 'Z':
                if (oppMove < 'C') {
                    selfMove = (char) (oppMove + MOVE_OFFSET_TO_OPP + 1);
                } else {
                    selfMove = (char) (oppMove + MOVE_OFFSET_TO_OPP - 2);
                }
                break;
            default:
                throw new RuntimeException("State is unknown and could not be set.");
        }
        return new Match(oppMove, selfMove);
    }
}
