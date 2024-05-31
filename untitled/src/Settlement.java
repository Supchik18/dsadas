import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Settlement {
    protected long amountCitizens;
    protected long treasury;
    protected List<String> tradeRelations;
    protected String settlementName;
    private static final Logger logger = Logger.getLogger(Settlement.class.getName());

    public Settlement(long amountCitizens, String settlementName) {
        this.amountCitizens = amountCitizens;
        this.settlementName = settlementName;
        this.treasury = 0;
        this.tradeRelations = new ArrayList<>();
    }

    public void addTreasury(long amount) {
        treasury += amount;
        logger.info("В " + settlementName + " теперь " + treasury + " золота");
    }

    public void removeTreasury(long amount) {
        if (amount > treasury) {
            logger.info("Казна " + settlementName + " пуста");
            treasury = 0;
        } else {
            treasury -= amount;
            logger.info("В " + settlementName + " теперь " + treasury + " золота");
        }
    }

    public void setTradeRelation(String otherSettlementName) {
        tradeRelations.add(otherSettlementName);
    }

    public void displayTradeRelations() {
        logger.info("Торговые отношения " + settlementName + " установлены с:");
        for (String relation : tradeRelations) {
            logger.info(relation);
        }
    }
}
