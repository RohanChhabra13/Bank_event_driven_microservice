package com.bank.loans.mapper;

import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto (Loans loans,LoansDto loansDto){
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans){
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loans.getTotalLoan());
        loans.setAmountPaid(loans.getAmountPaid());
        loans.setOutstandingAmount(loans.getOutstandingAmount());
        return loans;
    }
}
