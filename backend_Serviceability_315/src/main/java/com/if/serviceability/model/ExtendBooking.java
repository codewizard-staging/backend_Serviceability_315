package com.if.serviceability.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
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
import com.if.serviceability.converter.DurationConverter;
import com.if.serviceability.converter.UUIDToByteConverter;
import com.if.serviceability.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "ExtendBooking")
@Table(name = "\"ExtendBooking\"", schema =  "\"serviceability\"")
@Data
                        
public class ExtendBooking {
	public ExtendBooking () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"BookingID\"", nullable = true )
  private Integer bookingID;
	  
  @Column(name = "\"DateOfBooking\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date dateOfBooking;  
  
	  
  @Column(name = "\"FromDate\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date fromDate;  
  
	  
  @Column(name = "\"ToDate\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date toDate;  
  
	  
  @Column(name = "\"NewToDate\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date newToDate;  
  
  
  
  
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"ExtendBookingExtendbookingPayment\"", referencedColumnName = "\"PaymentID\"", insertable = false, updatable = false)
	private Payment extendbookingPayment;
	
	@Column(name = "\"ExtendBookingExtendbookingPayment\"")
	private Integer extendBookingExtendbookingPayment;
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "ExtendBooking [" 
  + "BookingID= " + bookingID  + ", " 
  + "DateOfBooking= " + dateOfBooking  + ", " 
  + "FromDate= " + fromDate  + ", " 
  + "ToDate= " + toDate  + ", " 
  + "NewToDate= " + newToDate 
 + "]";
	}
	
}