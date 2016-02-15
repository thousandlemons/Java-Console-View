package menu;

public final class DefaultIndexNumberFormatter implements IndexNumberFormatter{

    public static final DefaultIndexNumberFormatter INSTANCE = new DefaultIndexNumberFormatter();

    private DefaultIndexNumberFormatter(){

    }

    @Override
    public String format(int index) {
        return index + ") ";
    }
}
