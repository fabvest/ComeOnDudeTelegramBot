import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by fab on 12.05.2017.
 */
public class Main {
    public static void main(String... args){
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new ComeOnDudeBot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
