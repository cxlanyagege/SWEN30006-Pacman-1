package src;

public enum MonsterType {
    Troll,
    TX5,
    Orion;

    public String getImageName() {
        switch (this) {
            case Troll -> {
                return "m_troll.gif";
            }
            case TX5 -> {
                return "m_tx5.gif";
            }
            case Orion -> {
                return "m_orion.gif";
            }
            default -> {
                assert false;
            }
        }
        return null;
    }
}
