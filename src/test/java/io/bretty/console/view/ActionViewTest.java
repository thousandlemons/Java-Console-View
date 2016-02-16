package io.bretty.console.view;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ActionViewTest {

    public static void main(String[] args) {
        ActionView actionView = new ActionView("Testing", "") {
            @Override
            public void executeCustomAction() {
                System.out.println(this.read("int? ", Integer.class));
                System.out.println(this.read("double? ", Double.class));
                System.out.println(this.read("line? ", String.class));
                System.out.println(this.read("byte? ", Byte.class));
                System.out.println(this.read("boolean? ", Boolean.class));
                System.out.println(this.read("BigDecimal? ", BigDecimal.class));
                System.out.println(this.read("BigInteger? ", BigInteger.class));
                System.out.println(this.read("long? ", Long.class));
                System.out.println(this.read("short? ", Short.class));
                System.out.println(this.read("float? ", Float.class));
            }
        };
        actionView.display();
    }
}