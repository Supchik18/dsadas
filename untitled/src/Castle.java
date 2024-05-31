import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Castle extends Settlement {
    private long _amountArmy;
    private List<String> warCastles;
    private List<Castle> otherCastles;
    private List<String> relationsCastles;
    private Scanner scanner;
    private static final Logger logger = Logger.getLogger(Castle.class.getName());

    public Castle(long amountCitizens, String castleName) {
        super(amountCitizens, castleName);
        this._amountArmy = 0;
        this.warCastles = new ArrayList<>();
        this.otherCastles = new ArrayList<>();
        this.relationsCastles = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addArmy(long amount) {
        _amountArmy += amount;
        logger.info("В замке " + settlementName + " теперь " + _amountArmy + " солдат");
    }

    public void removeArmy(long amount) {
        if (amount > _amountArmy) {
            logger.info("Нельзя убрать солдат больше, чем есть в замке " + settlementName);
        } else {
            _amountArmy -= amount;
            logger.info("В замке " + settlementName + " теперь " + _amountArmy + " солдат");
        }
    }

    public void executeCitizens(int amount) {
        if (amount > amountCitizens) {
            logger.info("Нельзя казнить больше граждан, чем есть в замке " + settlementName);
        } else if (amount < 0) {
            logger.info("Количество граждан для казни должно быть больше нуля");
        } else {
            amountCitizens -= amount;
            logger.info("В замке " + settlementName + " теперь " + amountCitizens + " граждан");
        }
    }

    public void capitulate() {
        amountCitizens = 0;
        _amountArmy = 0;
        treasury = 0;
        logger.info("Замок " + settlementName + " капитулировал");
    }

    public void addOtherCastle(Castle castle) {
        otherCastles.add(castle);
    }

    public void displayOtherCastles() {
        logger.info("Список других замков:");
        for (Castle castle : otherCastles) {
            logger.info(castle.settlementName);
        }
    }

    public void announceWar(String enemyCastleName) {
        boolean found = false;
        for (Castle castle : otherCastles) {
            if (castle.settlementName.equals(enemyCastleName)) {
                if (warCastles.contains(enemyCastleName)) {
                    logger.info("Замок " + settlementName + " уже ведет войну с замком " + enemyCastleName);
                } else {
                    warCastles.add(enemyCastleName);
                    logger.info("Замок " + settlementName + " объявил войну замку " + enemyCastleName);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            logger.info("Замок с названием " + enemyCastleName + " не найден в списке");
        }
    }

    public void displayWarCastles() {
        logger.info("Замок " + settlementName + " ведет войну с:");
        for (String warCastle : warCastles) {
            logger.info(warCastle);
        }
    }

    public void makePeace(String enemyCastleName) {
        boolean found = false;
        for (String castle : warCastles) {
            if (castle.equals(enemyCastleName)) {
                warCastles.remove(enemyCastleName);
                logger.info("Замок " + settlementName + " заключил мир с замком " + enemyCastleName);
                found = true;
                break;
            }
        }
        if (!found) {
            logger.info("Замок " + settlementName + " не ведет войну с замком " + enemyCastleName);
        }
    }

    public void addArmyOrRemove(long amountSolders) {
        if (amountSolders < 0 && amountSolders > _amountArmy) {
            logger.info("Нельзя убрать солдат больше, чем есть по факту");
            return;
        }
        _amountArmy += amountSolders;
        logger.info("В вашем замке " + settlementName + " теперь " + _amountArmy + " солдат");
        checkCapitulation();
    }

    public void addTreasuryOrRemove(long amountGold) {
        treasury += amountGold;
        if (treasury < 0) {
            logger.info("Казна и так пуста");
            treasury = 0;
            return;
        }
        logger.info("В вашем замке " + settlementName + " теперь " + treasury + " золота");
        checkCapitulation();
    }

    public void doExecute(int amountExecutedCitizens) {
        if (amountExecutedCitizens > amountCitizens) {
            logger.info("Нельзя казнить столько людей, в замке " + settlementName + " всего " + amountCitizens + " граждан");
            return;
        } else if (amountExecutedCitizens < 0) {
            logger.info("Количество людей на казнь должно быть больше нуля");
            return;
        }
        amountCitizens -= amountExecutedCitizens;
        logger.info("В замке " + settlementName + " теперь " + amountCitizens + " граждан");
        checkCapitulation();
    }

    public void displayTradeRelations() {
        logger.info("Торговые отношения установлены с следующими королевствами:");
        for (String castle : relationsCastles) {
            logger.info(castle);
        }
    }

    public void removeTradeRelations() {
        logger.info("Выберите номер королевства с которым хотите разорвать торги");
        byte i = 0;
        for (String castle : relationsCastles) {
            logger.info(i + ". " + castle);
            i++;
        }
        byte numberCastle = scanner.nextByte();
        if (numberCastle >= 0 && numberCastle < relationsCastles.size()) {
            relationsCastles.remove(numberCastle);
        } else {
            logger.info("Неверный номер королевства");
        }
    }

    private void checkCapitulation() {
        if (amountCitizens == 0 && _amountArmy == 0 && treasury == 0) {
            capitulate();
            logger.info("Выход из программы, ваш замок капитулировал.");
            System.exit(0);
        }
    }
}