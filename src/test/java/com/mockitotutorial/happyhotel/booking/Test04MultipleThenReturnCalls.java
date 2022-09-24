package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test04MultipleThenReturnCalls {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setUp() {

        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_CountAvailablePlaces_When_CalledMultiple_Times() {

        //Given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(List.of(new Room("1", 5), new Room("3", 7)))
                .thenReturn(Collections.singletonList(new Room("3", 0)));

        ;
        //When
        int expectedFirst = 12;
        int expectedSecond = 0;

        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecond = bookingService.getAvailablePlaceCount();
        //Then
        assertAll(

                () -> assertEquals(expectedFirst, actualFirst),
                () -> assertEquals(expectedSecond, actualSecond)
        );
    }
}
