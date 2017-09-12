package me.iblitzkriegi.vixio.expressions.bot.runtime;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.effects.EffLogin;
import me.iblitzkriegi.vixio.registration.annotation.ExprAnnotation;
import org.bukkit.event.Event;

import java.util.Date;

/**
 * Created by Blitz on 11/2/2016.
 */
@ExprAnnotation.Expression(
        name = "RuntimeOfBot",
        title = "Runtime Of Bot",
        desc = "Get the Runtime of one of your Bot's in a special format",
        syntax = "(runtime|uptime) of (bot|user) %string%",
        returntype = String.class,
        type = ExpressionType.SIMPLE,
        example = "SUBMIT EXAMPLES TO Blitz#3273"
)
public class ExprRuntime extends SimpleExpression<String> {
    private Expression<String> vBotName;
    @Override
    protected String[] get(Event e) {
        return new String[]{getRuntime(e)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return getClass().getName();
    }

    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        vBotName = (Expression<String>) expr[0];
        return true;
    }
    private String getRuntime(Event e){
        if(EffLogin.botruntime.get(vBotName.getSingle(e))!=null){
            long seconds = getTimeRunning(e) / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
            return time;
        }else{
            return null;
        }
    }
    private long getTimeRunning(Event e){
        Date date = new Date();
        return (date.getTime() - EffLogin.botruntime.get(vBotName.getSingle(e)));
    }
}