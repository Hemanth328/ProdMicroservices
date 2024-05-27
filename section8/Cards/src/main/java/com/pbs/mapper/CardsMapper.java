package com.pbs.mapper;

import com.pbs.dto.CardsDto;
import com.pbs.entity.Card;

public class CardsMapper {

    public static Card mapToCard(Card card, CardsDto cardsDto) {

        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setCardType(cardsDto.getCardType());
        card.setCardLimit(cardsDto.getCardLimit());
        card.setLimitUsed(cardsDto.getLimitUsed());
        card.setAvailableBalance(card.getAvailableBalance());

        return card;
    }

    public static CardsDto mapToCardsDto(Card card, CardsDto cardsDto) {

        cardsDto.setMobileNumber(card.getMobileNumber());
        cardsDto.setCardNumber(card.getCardNumber());
        cardsDto.setCardType(card.getCardType());
        cardsDto.setCardLimit(card.getCardLimit());
        cardsDto.setLimitUsed(card.getLimitUsed());
        cardsDto.setAvailableBalance(card.getAvailableBalance());

        return cardsDto;
    }
}
