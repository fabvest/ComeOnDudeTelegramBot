import forismatic.Forismatic;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Date;
import java.util.Random;

/**
 * Created by fab on 12.05.2017.
 */
public class ComeOnDudeBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        Random r = new Random();
        String msg = update.getMessage().getText();

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(update.getMessage().getChatId());
//
            if(msg.equals("/getphrase")){
                Forismatic.Quote q = new Forismatic().getQuote();
                if(!q.getQuoteAuthor().equals(null) && !q.getQuoteText().equals(null)) {
                    message.setText(q.getQuoteText() + "\n" + q.getQuoteAuthor() + "\n\n"
                            + "via forismatic.com");
                }else if(q.getQuoteAuthor().equals(null)){
                    message.setText(q.getQuoteText() + "\n"
                            + "via forismatic.com");
                }

            }else if(msg.equals("/help")){
                message.setText("/getphrase - прислать какую-либо фразу" + "\n"
                                    + "/gettime - подстказать время" + "\n"
                                    + "/help - помощь");
            }else if(msg.equals("/gettime")){
                Date d = new Date();
                message.setText(d.toString());
            }else{
                message.setText("Я не понимаю тебя, создатель");
            }
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "Come_on_dude_bot";
    }

    public String getBotToken() {
        return "357160314:AAE3QMcCHHW3VrvKni92Xpr4VOUNzWsUknI";
    }

    String[] s = {
            "Каждый бот для чего-то создан, но создан человеком",
            "Только люди могут быть творцами ботов",
            "Сначала появился человек, а потом - бот",
            "Кто старое помянет - тому глаз вон",
            "Подлецу все к лицу",
            "Наполни день смыслом. Ну, или не наполняй",
            "Ты можешь сделать все, что захочешь. Ну, если у тебя есть время, " +
                    "здоровье, деньги, власть, ум и еще пару тройку незаменимых качеств",
            "Семь раз отмерь, один раз все брось.",
            "Без труда не вытащишь и рыбку из пруда, но ее без труда можно украсть"
    };
}
