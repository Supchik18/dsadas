import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        Castle castle = new Castle(userInput.getLongInput("Введите количество граждан: "),
                userInput.getStringInput("Введите название замка: "));

        int numOtherCastles = userInput.getIntInput("Введите количество других замков: ");
        for (int i = 0; i < numOtherCastles; i++) {
            long citizens = userInput.getLongInput("Введите количество граждан для замка " + (i + 1) + ": ");
            String name = userInput.getStringInput("Введите название замка " + (i + 1) + ": ");
            Castle otherCastle = new Castle(citizens, name);
            castle.addOtherCastle(otherCastle);
        }

        castle.displayOtherCastles();

        while (true) {
            logger.info("\nВыберите действие:");
            logger.info("1. Объявить войну");
            logger.info("2. Заключить мир");
            logger.info("3. Добавить/убрать солдат");
            logger.info("4. Добавить/убрать золото");
            logger.info("5. Казнить граждан");
            logger.info("6. Вывести торговые отношения");
            logger.info("7. Разорвать торговые отношения");
            logger.info("8. Выход");

            int choice = userInput.getIntInput("");
            switch (choice) {
                case 1:
                    logger.info("Введите название замка для объявления войны:");
                    String enemyCastle = userInput.getStringInput("");
                    castle.announceWar(enemyCastle);
                    break;
                case 2:
                    logger.info("Введите название замка для заключения мира:");
                    String peaceCastle = userInput.getStringInput("");
                    castle.makePeace(peaceCastle);
                    break;
                case 3:
                    logger.info("Введите количество солдат для добавления или удаления (отрицательное число для удаления):");
                    long amountSolders = userInput.getLongInput("");
                    castle.addArmyOrRemove(amountSolders);
                    break;
                case 4:
                    logger.info("Введите количество золота для добавления или удаления (отрицательное число для удаления):");
                    long amountGold = userInput.getLongInput("");
                    castle.addTreasuryOrRemove(amountGold);
                    break;
                case 5:
                    logger.info("Введите количество граждан для казни:");
                    int amountExecutedCitizens = userInput.getIntInput("");
                    castle.doExecute(amountExecutedCitizens);
                    break;
                case 6:
                    castle.displayTradeRelations();
                    break;
                case 7:
                    castle.removeTradeRelations();
                    break;
                case 8:
                    logger.info("Выход из приложения");
                    return;
                default:
                    logger.info("Неверный выбор, попробуйте еще раз");
            }

            castle.displayWarCastles();
        }
    }
}