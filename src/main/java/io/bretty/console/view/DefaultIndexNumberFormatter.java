package io.bretty.console.view;

public final class DefaultIndexNumberFormatter implements IndexNumberFormatter {


    /**
     * Singleton
     */
    public static final DefaultIndexNumberFormatter INSTANCE = new DefaultIndexNumberFormatter();

    private DefaultIndexNumberFormatter() {

    }

    /**
     *
     * @param index the index number of a menu item in a menu (starting from 0)
     * @return e.g. for index 0 (i.e the first one in menu items), the output is {@code "1) "}
     */
    @Override
    public String format(int index) {
        return (index + 1) + ") ";
    }
}
