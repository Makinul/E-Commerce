package com.makinul.ecommerce.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.makinul.ecommerce.data.model.Product
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : ProductRepository {
    override suspend fun allProducts(): List<Product>? {
        val result = database.getReference("products/items").childEventFlow().collect().wait()

        return null
    }

    override suspend fun product(id: String): Product? {
        val result = database.getReference("products/items").child(id).childEventFlow().collect().wait()

        return null
    }

//    suspend fun DatabaseReference.singleValueEvent(): SingleEventResponse =
//        suspendCoroutine { continuation ->
//            val valueEventListener = object : ValueEventListener {
//                override fun onCancelled(error: DatabaseError) {
//                    continuation.resume(EventResponse.Cancelled(error))
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    continuation.resume(EventResponse.Changed(snapshot))
//                }
//            }
//            addListenerForSingleValueEvent(valueEventListener) // Subscribe to the event
//        }
//
//    private suspend fun call() {
//        val result = database.getReference("items").singleValueEvent()
//        when (result) {
//            is EventResponse.Changed -> {
//                val snapshot = result.snapshot
//            }
//            is EventResponse.Cancelled -> {
//                val message = result.error.toException().message
//                Log.e("TAG", "Error: $message")
//            }
//        }
//    }
//
//    suspend fun DatabaseReference.valueEventFlow(): Flow<EventResponse> =
//        callbackFlow {
//            val valueEventListener = object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot): Unit =
//                    sendBlocking(EventResponse.Changed(snapshot))
//
//                override fun onCancelled(error: DatabaseError): Unit =
//                    sendBlocking(EventResponse.Cancelled(error))
//            }
//            addValueEventListener(valueEventListener)
//            awaitClose {
//                removeEventListener(valueEventListener)
//            }
//        }
//

}