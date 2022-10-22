package willer.app.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakePaymentCommand {

    private final Basket basket;

}
