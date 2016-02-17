package io.bretty.console.view;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ActionViewTest {

    public static void main(String[] args) {
        ActionView actionView = new ActionView("Testing", "") {
            @Override
            public void executeCustomAction() {

                this.pause();

                System.out.println(this.prompt("int? ", Integer.class));
                System.out.println(this.prompt("double? ", Double.class));
                System.out.println(this.prompt("line? ", String.class));
                System.out.println(this.prompt("byte? ", Byte.class));
                System.out.println(this.prompt("boolean? ", Boolean.class));
                System.out.println(this.prompt("BigDecimal? ", BigDecimal.class));
                System.out.println(this.prompt("BigInteger? ", BigInteger.class));
                System.out.println(this.prompt("long? ", Long.class));
                System.out.println(this.prompt("short? ", Short.class));
                System.out.println(this.prompt("float? ", Float.class));
            }
        };
        actionView.display();
    }
}