package ru.kpfu.itis.skillshare.mainservice.security.util;


import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class XssFilterUtil {

    public static String protect(String unsafeContent) {
        Safelist safelist = Safelist.none()
                // Разрешаем текстовые форматирования
                .addTags("b", "i", "u", "strong", "em", "sub", "sup")
                // Разрешаем блочные элементы
                .addTags("p", "h1", "h2", "h3", "h4", "h5", "h6", "div", "blockquote")
                // Разрешаем ссылки и изображения с указанными атрибутами
                .addTags("a", "img")
                .addAttributes("a", "href", "title")
                .addAttributes("img", "src", "alt", "title")
                // Разрешаем списки и их элементы
                .addTags("ul", "ol", "li")
                // Разрешаем таблицы и их элементы
                .addTags("table", "thead", "tbody", "tfoot", "tr", "th", "td")
                .addTags("code", "pre")
                // Для кода
                .addAttributes("pre", "data-language", "spellcheck")
                .addTags("br")
                // Разрешаем стили для всех тегов
                .addAttributes(":all", "style", "class");

        return Jsoup.clean(unsafeContent, safelist);
    }
}
