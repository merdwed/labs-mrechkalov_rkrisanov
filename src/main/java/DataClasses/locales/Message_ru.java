package DataClasses.locales;

import java.util.ListResourceBundle;

public class Message_ru extends ListResourceBundle {
    private final Object[][]   contents =
            {
                    {"Wrong user", "Неверный польхователь. Нет доступа"},
                    {"Wrong user\nAccount isn't exist", "Неверный польхователь\nАккаунта не существует"},
                    {"Ticket added successful", "Билет успешно добавлен"},
                    {"Ticket deleted successful", "Билет успешно удалён"},
                    {"Ticket updated successful", "Билет успешно обновлен"},
                    {"Ticket didn't add", "Билет не был добавлен}"},
                    {"User's tickets is cleaned up", "Билеты пользователя успешно удалены"},
                    {"Every ticket from the collection less the ticket is removed", "Каждый билет, меньший текущего удален"},
                    {"Account created successful", "Аккаунт успешно создан"},
                    {"Account deleted successful", "Аккаунт успешно удален"},
                    {"Collection sorted by price", "Коллекция отсортирована по цене"},
                    {"Collection sorted less by ticketType", "Коллекция отсортирована по типу билета"},
                    {"Collection sorted by name", "Коллекция отсортирована по имени"},
                    {"Ticket not found", "Билет не найдён"},
                    {"Collection:", "Коллекция:"},
                    {"id", "id"},
                    {"name", "имя"},
                    {"price", "цена"},
                    {"location X", "расположение X"},
                    {"location Y", "расположение Y"},
                    {"location Z", "расположение Z"},
                    {"coordinates X", "координаты X"},
                    {"coordinates Y", "координаты Y"},
                    {"type", "тип"},
                    {"creator", "создатель"},
                    {"person", "персона"},
                    {"location", "расположение"},
                    {"user", "пользователь"},
                    {"password", "пароль"},
                    {"edit", "редактировать"},
                    {"host", "хост"},
                    {"port", "порт"},
                    {"check server", "проверить сервер"},
                    {"add", "добавить"},
                    {"account", "аккаунт"},
                    {"sign in", "войти"},
                    {"create account", "создать аккаунт"},
                    {"save", "сохранить"},
                    {"delete", "удалить"},
                    {"size", "размер"},
                    {"clear", "очистить"},
                    {"show", "показать"},
                    {"height", "высота"},
                    {"weight", "вес"},
                    {"sign out", "выйти"},
                    {"cancel", "отмена"},
            };

    public Object[][] getContents() {return contents; }
}
