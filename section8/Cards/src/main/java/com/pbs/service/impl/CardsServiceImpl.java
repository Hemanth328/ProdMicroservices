package com.pbs.service.impl;

import com.pbs.constants.CardConstants;
import com.pbs.dto.CardsDto;
import com.pbs.entity.Card;
import com.pbs.exceptions.CardAlreadyExistsException;
import com.pbs.exceptions.ResourceNotFoundException;
import com.pbs.mapper.CardsMapper;
import com.pbs.repo.CardRepo;
import com.pbs.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardRepo cardRepo;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {

        Optional<Card> customer = cardRepo.findByMobileNumber(mobileNumber);

        if (customer.isPresent())
            throw new CardAlreadyExistsException("Customer already exists with the given mobile number");
        else
            cardRepo.save(createNewCard(mobileNumber));

    }

    private Card createNewCard(String mobile) {

        Card card = new Card();

        card.setCardLimit(CardConstants.NEW_CARD_LIMIT);
        card.setCardType(CardConstants.CREDIT_CARD);

        long cardNumber = 400000000000L + new Random().nextInt(900000000);

        card.setCardNumber(Long.toString(cardNumber));
        card.setAvailableBalance(CardConstants.NEW_CARD_LIMIT);
        card.setLimitUsed(0);
        card.setMobileNumber(mobile);

        return card;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {

        Card card = cardRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));

        return CardsMapper.mapToCardsDto(card, new CardsDto());
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Card card = cardRepo.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number", cardsDto.getCardNumber())
        );

        cardRepo.save(CardsMapper.mapToCard(card, cardsDto));
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if card deletion is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {

        Card card = cardRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );

        cardRepo.deleteById(card.getCardId());

        return true;
    }
}
