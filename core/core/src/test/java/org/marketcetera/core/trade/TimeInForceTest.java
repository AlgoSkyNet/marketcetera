package org.marketcetera.core.trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.marketcetera.core.Pair;

import static org.marketcetera.core.trade.TimeInForce.*;

/* $License$ */
/**
 * Tests {@link org.marketcetera.core.trade.TimeInForce}
 *
 * @author anshul@marketcetera.com
 * @version $Id: TimeInForceTest.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
public class TimeInForceTest extends FIXCharEnumTestBase <TimeInForce>{
    @Override
    protected TimeInForce getInstanceForFIXValue(Character inFIXValue) {
        return TimeInForce.getInstanceForFIXValue(inFIXValue);
    }

    @Override
    protected Character getFIXValue(TimeInForce e) {
        return e.getFIXValue();
    }

    @Override
    protected TimeInForce unknownInstance() {
        return Unknown;
    }

    @Override
    protected List<TimeInForce> getValues() {
        return Arrays.asList(values());
    }

    @Override
    protected List<Pair<TimeInForce, Character>> knownValues()
    {
        List<Pair<TimeInForce,Character>> values = new ArrayList<Pair<TimeInForce,Character>>();
        values.add(new Pair<TimeInForce,Character>(Day, quickfix.field.TimeInForce.DAY));
        values.add(new Pair<TimeInForce,Character>(GoodTillCancel, quickfix.field.TimeInForce.GOOD_TILL_CANCEL));
        values.add(new Pair<TimeInForce,Character>(AtTheOpening, quickfix.field.TimeInForce.AT_THE_OPENING));
        values.add(new Pair<TimeInForce,Character>(ImmediateOrCancel, quickfix.field.TimeInForce.IMMEDIATE_OR_CANCEL));
        values.add(new Pair<TimeInForce, Character>(FillOrKill, quickfix.field.TimeInForce.FILL_OR_KILL));
        values.add(new Pair<TimeInForce, Character>(AtTheClose, quickfix.field.TimeInForce.AT_THE_CLOSE));
        return values;
    }
}