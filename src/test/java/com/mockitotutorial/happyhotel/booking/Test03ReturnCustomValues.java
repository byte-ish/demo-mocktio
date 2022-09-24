package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03ReturnCustomValues {

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
    void should_CountAvailablePlaces_WhenOneRoom() {

        //Given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1", 2)));
        //When
        int expected = 2;

        int actual = bookingService.getAvailablePlaceCount();
        //Then
        assertEquals(expected, actual);
    }

    @Test
    void should_CountAvailablePlaces_WhenTwoRoom() {

        //Given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(List.of(new Room("1", 5), new Room("3", 7)));
        //When
        int expected = 12;

        int actual = bookingService.getAvailablePlaceCount();
        //Then
        assertEquals(expected, actual);
    }
}
