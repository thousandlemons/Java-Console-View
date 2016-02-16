package io.bretty.console.view;

public final class DefaultIndexNumberFormatter implements IndexNumberFormatter {

    /**
     * e.g. for index 0 (i.e the first one in menu items), the output is "1) "
     */

    public static final DefaultIndexNumberFormatter INSTANCE = new DefaultIndexNumberFormatter();

    private DefaultIndexNumberFormatter() {

    }

    @Override
    public String format(int index) {
        return (index + 1) + ") ";
    }
}
