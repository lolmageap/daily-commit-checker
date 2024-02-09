package com.example.filialscheduler.dto

import java.time.ZonedDateTime
import java.util.UUID

class TossRequest() {
    lateinit var apiKey: String
    val productDesc: String = "부모님께 송금"
    val orderNo: String = UUID.randomUUID().toString()
    val retUrl: String = "https://toss.im"
    val retCancelUrl: String = "https://toss.im"
    val retAppScheme: String = "toss"
    val autoExecute: Boolean = true
    val resultCallback: String = "https://toss.im"
    val callbackVersion: Boolean = true
    val amount: Int = 10_000
    val amountTaxFree: Int = 0
    val amountTaxable: Int = 10_000
    val amountVat: Int = 0
    val amountServiceFee: Int = 0
    val expiredTime: ZonedDateTime = ZonedDateTime.now().plusDays(1)
    val enablePayMethods: String = "CARD"
    val cashReceipt: String = "PERSONAL"
    val cashReceiptTradeOption: String = "CASH"
    val cardOptions: String = "INTEREST_FREE"
    val installment: String = "CARD"
}