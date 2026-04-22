///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS org.json:json:20251224
//DEPS dev.tamboui:tamboui-toolkit:0.2.0-SNAPSHOT
//DEPS dev.tamboui:tamboui-jline3-backend:0.2.0-SNAPSHOT

//NATIVE_OPTIONS -H:IncludeResources=.*\.properties

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import dev.tamboui.style.Overflow;
import dev.tamboui.style.Style;
import dev.tamboui.text.Text;
import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.event.EventResult;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.widgets.paragraph.Paragraph;

import static dev.tamboui.toolkit.Toolkit.*;


public class GetCatFacts extends ToolkitApp {

    public static final String banner = """
:'######:::::'###::::'########:'########::::'###:::::'######::'########::'######::
'##... ##:::'## ##:::... ##..:: ##.....::::'## ##:::'##... ##:... ##..::'##... ##:
##:::..:::'##:. ##::::: ##:::: ##::::::::'##:. ##:: ##:::..::::: ##:::: ##:::..::
##:::::::'##:::. ##:::: ##:::: ######:::'##:::. ##: ##:::::::::: ##::::. ######::
##::::::: #########:::: ##:::: ##...:::: #########: ##:::::::::: ##:::::..... ##:
##::: ##: ##.... ##:::: ##:::: ##::::::: ##.... ##: ##::: ##:::: ##::::'##::: ##:
. ######:: ##:::: ##:::: ##:::: ##::::::: ##:::: ##:. ######::::: ##::::. ######::
:......:::..:::::..:::::..:::::..::::::::..:::::..:::......::::::..::::::......:::
""";

    public record CatFact(String fact, int length) {}

    private static final HttpClient client = HttpClient.newHttpClient();

    private static CatFact catFact;

    public static void main(String[] args) throws Exception{
        catFact = getFact();
        new GetCatFacts().run();
    }

    @Override
    protected Element render() {
        return panel(
            richTextArea(banner).bold().cyan(),
            spacer(),
            widget(Paragraph.builder()
                .text(Text.from(catFact.fact))
                .overflow(Overflow.WRAP_WORD)
                .style(Style.create().yellow())
                .build()
            ).yellow(),
            spacer(),
            text("Press 'q' to quit").dim(),
            text("Press any other key for another cat fact").dim()
        ).rounded()
        .onKeyEvent(this::handleKey);
    }

    private EventResult handleKey(KeyEvent event) {
        if (event.isCtrlC() || event.isChar('q')) {
            return EventResult.UNHANDLED;
        }
        catFact = getFact();
        return EventResult.HANDLED;
    }


    public static CatFact getFact() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://catfact.ninja/fact"))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            return new CatFact(json.getString("fact"), json.getInt("length"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}