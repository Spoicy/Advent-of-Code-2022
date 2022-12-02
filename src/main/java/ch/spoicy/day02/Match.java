package ch.spoicy.day02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Match {

    public static final int MOVE_OFFSET_TO_OPP = 23;
    public static final int MOVE_OFFSET_TO_RESULT = 32;
    public static final int MOVE_OFFSET_TYPECAST = 55 + MOVE_OFFSET_TO_OPP;
    private final char oppMove;
    private final char selfMove;
    private static final Logger LOG =
            LogManager.getLogger(Match.class);
    public Match(final char oppMove, final char moveOrState, final Mode mode) {
        this.oppMove = oppMove;
        if (mode.equals(Mode.MOVES)) {
            this.selfMove = moveOrState;
            return;
        }
        switch (moveOrState) {
            case 'X':
                if (oppMove > 'A') {
                    this.selfMove = (char) (Character.getNumericValue(oppMove) + MOVE_OFFSET_TYPECAST - 1);
                } else {
                    this.selfMove = (char) (Character.getNumericValue(oppMove) + MOVE_OFFSET_TYPECAST + 2);
                }
                break;
            case 'Y':
                this.selfMove = (char) (Character.getNumericValue(oppMove) + MOVE_OFFSET_TYPECAST);
                break;
            case 'Z':
                if (oppMove < 'C') {
                    this.selfMove = (char) (Character.getNumericValue(oppMove) + MOVE_OFFSET_TYPECAST + 1);
                } else {
                    this.selfMove = (char) (Character.getNumericValue(oppMove) + MOVE_OFFSET_TYPECAST - 2);
                }
                break;
            default:
                throw new RuntimeException("State is unknown and could not be set.");
        }
    }

    public char getOppMove() {
        return oppMove;
    }

    public char getSelfMove() {
        return selfMove;
    }

    public int getMatchValue() {
        int result = Character.getNumericValue(selfMove) - Character.getNumericValue(oppMove) - MOVE_OFFSET_TO_OPP;
        int moveValue = Character.getNumericValue(selfMove) - MOVE_OFFSET_TO_RESULT;
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
}
