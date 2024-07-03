package com.if.serviceability.function;

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
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.if.serviceability.repository.VerficationRepository;
import com.if.serviceability.repository.PaymentRepository;
import com.if.serviceability.repository.CustomerRepository;
import com.if.serviceability.repository.InsuranceRepository;
import com.if.serviceability.repository.ServiceCrewRepository;
import com.if.serviceability.repository.ReturnRentedBikeRepository;
import com.if.serviceability.repository.BookingRepository;
import com.if.serviceability.repository.RoadsideAssistanceRepository;
import com.if.serviceability.repository.ExtendBookingRepository;
import com.if.serviceability.repository.RentalCompanyRepository;
import com.if.serviceability.repository.ReturnBikeInspectionRepository;
import com.if.serviceability.repository.BikeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
