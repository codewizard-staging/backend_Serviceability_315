package com.if.serviceability.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

import com.if.serviceability.model.RoadsideAssistance;
import com.if.serviceability.model.Verfication;
import com.if.serviceability.model.Payment;
import com.if.serviceability.model.ServiceCrew;
import com.if.serviceability.model.Insurance;
import com.if.serviceability.model.ExtendBooking;
import com.if.serviceability.model.Booking;
import com.if.serviceability.model.RentalCompany;
import com.if.serviceability.model.ReturnBikeInspection;
import com.if.serviceability.model.Customer;
import com.if.serviceability.model.Bike;
import com.if.serviceability.model.ReturnRentedBike;
import com.if.serviceability.enums.IDTypes;
import com.if.serviceability.converter.IDTypesConverter;

@Entity(name = "ReturnRentedBikeInspection")
@Table(schema = "\"serviceability\"", name = "\"ReturnRentedBikeInspection\"")
@Data
public class ReturnRentedBikeInspection{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"BookingID\"")
	private Integer bookingID;

    
    @Column(name = "\"BikeID\"")
    private Integer bikeID;
 
}