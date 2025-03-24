package com.example.flupperapp.data
import androidx.room.Entity
import androidx.room.PrimaryKey

// если у нас два значения то мы записываем для второго значения id а оно будет тоже примари кей а эти методанные сверху всегда над тем к чему относятся?
@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey var id: Int = 1,
    var amount: Int = 0
)