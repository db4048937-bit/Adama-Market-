package com.example.data.model

import com.example.R

object ProductCatalog {
    const val OWNER_PHONE = "+251992558349"
    const val TELEGRAM_CHANNEL = "https://t.me/Adama_Shopiify"

    val ADAMA_LOCATIONS = listOf(
        "Posta Bet (Central Market)",
        "Bole Adama (ASTU Avenue)",
        "Boku Shenen Commercial Hub",
        "Geda Plaza & Kebele 04",
        "Franco / Rift Valley Road",
        "Melka Adama Area",
        "Adama Tera Bus Station"
    )

    val MAIN_CATEGORIES = listOf(
        "All Items",
        "Phone",
        "Car",
        "Home",
        "Electric"
    )

    val PRODUCTS = listOf(
        // ================= PHONE =================
        Product(
            id = "phone_01",
            name = "iPhone 15 Pro Max (256GB Natural Titanium)",
            category = "Phone",
            priceBirr = 178000.0,
            originalPriceBirr = 192000.0,
            rating = 5.0f,
            reviewsCount = 48,
            imageResId = R.drawable.adama_phones_mobiles_1790195746048,
            description = "Original factory sealed iPhone 15 Pro Max with A17 Pro chip, Grade 5 titanium body, 48MP Pro camera system with 5x telephoto, and Action button. Official 1-year Apple international warranty.",
            locationInAdama = "Posta Bet Digital Mall, Adama",
            tags = listOf("Factory Sealed", "1-Yr Warranty", "Best Price"),
            variants = listOf("Natural Titanium", "Blue Titanium", "Black Titanium"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = true,
            condition = "Brand New / Sealed"
        ),
        Product(
            id = "phone_02",
            name = "Samsung Galaxy S24 Ultra (512GB AI Edition)",
            category = "Phone",
            priceBirr = 165000.0,
            originalPriceBirr = 175000.0,
            rating = 4.9f,
            reviewsCount = 37,
            imageResId = R.drawable.adama_phones_mobiles_1790195746048,
            description = "Galaxy AI powerhouse with Snapdragon 8 Gen 3, integrated S-Pen stylus, 200MP camera with 100x Space Zoom, 120Hz Dynamic AMOLED display, and 5000mAh battery.",
            locationInAdama = "Bole Adama Tech District",
            tags = listOf("Galaxy AI", "S-Pen Included"),
            variants = listOf("Titanium Gray", "Titanium Black", "Titanium Violet"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = true,
            condition = "Brand New"
        ),
        Product(
            id = "phone_03",
            name = "Redmi Note 13 Pro+ 5G (12GB RAM / 512GB)",
            category = "Phone",
            priceBirr = 54000.0,
            originalPriceBirr = 59000.0,
            rating = 4.8f,
            reviewsCount = 62,
            imageResId = R.drawable.adama_phones_mobiles_1790195746048,
            description = "Super fast 120W HyperCharge (0-100% in 19 mins), 200MP OIS camera, curved 1.5K 120Hz AMOLED display, IP68 water & dust resistance.",
            locationInAdama = "Geda Plaza Tech Zone, Adama",
            tags = listOf("120W Fast Charge", "200MP Camera"),
            variants = listOf("Midnight Black", "Aurora Purple", "Moonlight White"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Brand New in Box"
        ),

        // ================= CAR =================
        Product(
            id = "car_01",
            name = "Toyota Vitz 2018 (Automated, Clean Title)",
            category = "Car",
            priceBirr = 1850000.0,
            originalPriceBirr = 1950000.0,
            rating = 4.9f,
            reviewsCount = 24,
            imageResId = R.drawable.adama_cars_market_1790195706596,
            description = "Pristine Toyota Vitz 2018 model, fuel-efficient 1.0L engine, automatic transmission, push-to-start ignition, reverse camera, chilling AC, and 100% original factory paint. Inspection welcome in Adama.",
            locationInAdama = "Franco Avenue Auto Hub, Adama",
            tags = listOf("Clean Title", "Accident Free", "Original Paint"),
            variants = listOf("Pearl White", "Silver Metallic"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = true,
            condition = "Super Clean / Used"
        ),
        Product(
            id = "car_02",
            name = "Toyota Corolla Executive (2021 Model)",
            category = "Car",
            priceBirr = 3400000.0,
            originalPriceBirr = 3600000.0,
            rating = 5.0f,
            reviewsCount = 18,
            imageResId = R.drawable.adama_cars_market_1790195706596,
            description = "Executive luxury Toyota Corolla sedan with full leather interior, sunroof, digital cockpit display, lane departure warning, radar cruise control, and low mileage. Verified documents ready for name transfer.",
            locationInAdama = "Bole Adama VIP Drive",
            tags = listOf("Full Leather", "Sunroof", "Low Mileage"),
            variants = listOf("Classic Black", "Glacier White"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = false,
            condition = "Like New / Pristine"
        ),
        Product(
            id = "car_03",
            name = "Bajaj TVS King Deluxe (2023 Commercial Bajaj)",
            category = "Car",
            priceBirr = 390000.0,
            originalPriceBirr = 425000.0,
            rating = 4.8f,
            reviewsCount = 31,
            imageResId = R.drawable.adama_cars_market_1790195706596,
            description = "High earning 4-stroke commercial 3-wheeler Bajaj in excellent mechanical condition. Durable chassis, low fuel consumption, brand new tires, and registered Oromia plates.",
            locationInAdama = "Adama Bus Station (Tera) Area",
            tags = listOf("Commercial Ready", "Low Fuel Consumption"),
            variants = listOf("Bright Blue", "Green Yellow"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Well Maintained"
        ),

        // ================= HOME =================
        Product(
            id = "home_01",
            name = "Modern 3-Bedroom Luxury Villa (Bole Adama)",
            category = "Home",
            priceBirr = 9500000.0,
            originalPriceBirr = 10500000.0,
            rating = 5.0f,
            reviewsCount = 15,
            imageResId = R.drawable.adama_home_realestate_1790195719081,
            description = "Stunning newly built modern compound villa on 250 sqm plot in prime Bole Adama. Features 3 en-suite bedrooms, open Italian kitchen, spacious salon with gypsum lighting, paved compound for 3 cars, water reservoir tank, and clean legal title deed.",
            locationInAdama = "Bole Adama (Near ASTU Gate)",
            tags = listOf("Title Deed Ready", "Paved Compound", "Water Tank"),
            variants = listOf("250 sqm Compound", "Finished Ready to Inhabit"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = true,
            condition = "Brand New Construction"
        ),
        Product(
            id = "home_02",
            name = "2-Bedroom Luxury Furnished Apartment (Rent/Month)",
            category = "Home",
            priceBirr = 45000.0,
            originalPriceBirr = 50000.0,
            rating = 4.9f,
            reviewsCount = 28,
            imageResId = R.drawable.adama_home_realestate_1790195719081,
            description = "Fully furnished modern 2-bedroom apartment with 24/7 security guard, automatic backup generator, Wi-Fi internet, hot water, modern sofa and king bed set, smart TV, and balcony overlooking Adama hills.",
            locationInAdama = "Posta Bet Central, Adama",
            tags = listOf("Fully Furnished", "Generator Backup", "Wi-Fi"),
            variants = listOf("Monthly Rental", "Quarterly Lease"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Ready to Move In"
        ),
        Product(
            id = "home_03",
            name = "Royal 7-Seater Velvet Living Room Sofa Set",
            category = "Home",
            priceBirr = 85000.0,
            originalPriceBirr = 98000.0,
            rating = 4.8f,
            reviewsCount = 42,
            imageResId = R.drawable.adama_home_realestate_1790195719081,
            description = "Handcrafted high-density foam 7-seater sectional sofa with stain-resistant velvet fabric, solid hardwood frame, and gold accent legs. Includes matching center coffee table and 4 cushions.",
            locationInAdama = "Boku Shenen Furniture Street",
            tags = listOf("Hardwood Frame", "Coffee Table Included"),
            variants = listOf("Navy & Gold", "Emerald Green", "Warm Beige"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Brand New / Workshop Direct"
        ),

        // ================= ELECTRIC =================
        Product(
            id = "electric_01",
            name = "Samsung 55\" Crystal 4K UHD Smart TV (2024)",
            category = "Electric",
            priceBirr = 69000.0,
            originalPriceBirr = 75000.0,
            rating = 4.9f,
            reviewsCount = 53,
            imageResId = R.drawable.adama_electric_gadgets_1790195734364,
            description = "Genuine Samsung 55-inch Ultra HD 4K television with HDR10+, Dynamic Crystal Color, built-in YouTube, Netflix, Apple AirPlay, and optical audio output. Comes in original box with 2-year warranty.",
            locationInAdama = "Posta Bet Electronics Mall, Adama",
            tags = listOf("Genuine Samsung", "2-Year Warranty", "4K Ultra HD"),
            variants = listOf("55-Inch Model", "65-Inch Model (+25,000 ETB)"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            isHotDeal = true,
            condition = "Brand New in Box"
        ),
        Product(
            id = "electric_02",
            name = "3.2kVA Pure Sine Wave Solar Hybrid Inverter + Battery",
            category = "Electric",
            priceBirr = 92000.0,
            originalPriceBirr = 104000.0,
            rating = 5.0f,
            reviewsCount = 38,
            imageResId = R.drawable.adama_electric_gadgets_1790195734364,
            description = "Heavy duty hybrid inverter system capable of running refrigerators, televisions, lights, and desktop computers continuously during power outages. Includes 200Ah deep-cycle gel battery and installation guide.",
            locationInAdama = "Geda Solar Energy Solutions, Adama",
            tags = listOf("Zero Outages", "Deep Cycle Gel", "Pure Sine Wave"),
            variants = listOf("3.2kVA Complete Kit", "5kVA Heavy Duty Kit"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Brand New Complete Kit"
        ),
        Product(
            id = "electric_03",
            name = "LG 320L Double-Door Smart Inverter Refrigerator",
            category = "Electric",
            priceBirr = 78000.0,
            originalPriceBirr = 86000.0,
            rating = 4.8f,
            reviewsCount = 29,
            imageResId = R.drawable.adama_electric_gadgets_1790195734364,
            description = "Linear Inverter compressor refrigerator with DoorCooling+, multi-air flow vents, energy-saving frost-free operation, and tempered glass shelves. 10-year compressor warranty.",
            locationInAdama = "Bole Adama Appliances Center",
            tags = listOf("10-Yr Compressor Warranty", "Energy Efficient"),
            variants = listOf("Shiny Steel", "Matte Dark Silver"),
            sellerName = "Adama Market Admin",
            sellerPhone = OWNER_PHONE,
            sellerTelegram = TELEGRAM_CHANNEL,
            condition = "Brand New with Warranty"
        )
    )
}
