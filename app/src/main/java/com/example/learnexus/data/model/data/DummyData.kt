package com.example.learnexus.data

import com.example.learnexus.data.model.*

object DummyData {

    val courses = listOf(
        // --- COURSE 1: MEMAHAMI DASAR KOTLIN ---
        Course(
            id = "kotlin_101",
            title = "Memahami Dasar Kotlin",
            description = "Pemrograman Aplikasi Mobile",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Mobile", "Android", "Coding"),
            modules = listOf(

                // 1. PERKENALAN KOTLIN (ARTIKEL)
                Module(
                    id = 101,
                    title = "Perkenalan Kotlin",
                    description = "Sejarah singkat dan alasan Google memilih Kotlin.",
                    duration = "5 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                    # Pengenalan & Sejarah Kotlin
                    
                    **Apa itu Kotlin?**
                    Kotlin adalah bahasa pemrograman modern yang dibuat oleh JetBrains (perusahaan di balik IntelliJ IDEA dan Android Studio). Bahasa ini dirancang agar ringkas, ekspresif, dan aman dari error umum yang sering terjadi di bahasa lama.
                    
                    **Sejarah Singkat**
                    * 2011: JetBrains memperkenalkan Kotlin. Namanya diambil dari sebuah pulau di dekat St. Petersburg, Rusia (mirip seperti Java yang diambil dari nama pulau di Indonesia).
                    * 2017: Google mengumumkan dukungan resmi ("First Class Support") untuk Kotlin di Android.
                    * 2019: Google mengumumkan "Kotlin First". Artinya, semua fitur baru Android akan dikembangkan dengan prioritas utama untuk Kotlin.
                    
                    **Kenapa Harus Pindah dari Java?**
                    Banyak developer senior dulunya menggunakan Java. Mengapa sekarang semua beralih ke Kotlin?
                    1. Lebih Ringkas (Concise): Kotlin memangkas kode yang bertele-tele (boilerplate). Apa yang butuh 50 baris di Java, seringkali hanya butuh 10-15 baris di Kotlin.
                    2. Aman dari Null (Null Safety): Masalah terbesar di aplikasi Android adalah NullPointerException (aplikasi tiba-tiba menutup sendiri/force close). Kotlin memiliki fitur bawaan untuk mencegah hal ini terjadi sejak kodingan ditulis.
                    3. 100% Kompatibel: Kamu bisa menggunakan kode Java dan Kotlin bersamaan dalam satu proyek. Jadi, tidak perlu membuang kodingan lama.
                    
                    **Kesimpulan**
                    Belajar Kotlin adalah investasi terbaik untuk karir Android Developer saat ini. Di modul selanjutnya, kita akan langsung mempraktikkan kode pertama kita!
                """.trimIndent()
                    )
                ),

                // 2. DASAR-DASAR KOTLIN (VIDEO)
                Module(
                    id = 102,
                    title = "Dasar-dasar Kotlin",
                    description = "Video panduan memulai project pertama.",
                    duration = "10 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=F9UC9DY-vIU" // Link Video Dummy
                    )
                ),

                // 3. VARIABEL, TIPE DATA & CONTROL FLOW (ARTIKEL)
                Module(
                    id = 103,
                    title = "Variabel, Tipe Data & Control Flow",
                    description = "Mempelajari Val/Var, Tipe Data, If-Else, dan When.",
                    duration = "15 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # 1. Variabel & Tipe Data
                            
                            **Val (Immutable):**
                            Data yang tidak bisa berubah (kotak terkunci). Gunakan ini secara default.
                            ```kotlin
                            val nama = "Learnexus"
                            // nama = "Baru" // Error!
                            ```
                            
                            **Var (Mutable):**
                            Data yang bisa berubah (kotak terbuka).
                            ```kotlin
                            var skor = 0
                            skor = 10 // Boleh
                            ```

                            # 2. Control Flow (Logika)
                            
                            **If-Else Expression:**
                            Di Kotlin, `if` bisa mengembalikan nilai langsung ke variabel.
                            ```kotlin
                            val status = if (skor > 50) "Lulus" else "Remidi"
                            ```
                            
                            **When Expression:**
                            Pengganti Switch-Case yang lebih powerful dan ringkas.
                            ```kotlin
                            when (status) {
                                "Lulus" -> print("Selamat!")
                                else -> print("Belajar lagi ya.")
                            }
                            ```
                        """.trimIndent()
                    )
                ),

                // 4. KUIS (QUIZ - 10 SOAL)
                Module(
                    id = 104,
                    title = "Kuis Kotlin",
                    description = "Ujian akhir pemahaman materi Kotlin.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Keyword untuk membuat variabel yang nilainya TETAP (tidak bisa diubah)?",
                                options = listOf("var", "let", "val", "const"),
                                correctAnswerIndex = 2 // val
                            ),
                            Question(
                                text = "Fitur pengganti Switch-Case di Kotlin disebut?",
                                options = listOf("when", "check", "switch", "case"),
                                correctAnswerIndex = 0 // when
                            ),
                            Question(
                                text = "Simbol yang digunakan untuk String Template?",
                                options = listOf("#", "$", "@", "%"),
                                correctAnswerIndex = 1 // $
                            ),
                            Question(
                                text = "Tahun berapa Google meresmikan dukungan Kotlin untuk Android?",
                                options = listOf("2015", "2016", "2017", "2018"),
                                correctAnswerIndex = 2 // 2017
                            ),
                            Question(
                                text = "Fungsi utama yang dijalankan pertama kali dalam program Kotlin?",
                                options = listOf("start()", "main()", "run()", "init()"),
                                correctAnswerIndex = 1 // main()
                            ),
                            Question(
                                text = "Apa ekstensi file untuk kode sumber Kotlin?",
                                options = listOf(".java", ".kt", ".xml", ".kts"),
                                correctAnswerIndex = 1 // .kt
                            ),
                            Question(
                                text = "Perintah untuk mencetak teks ke layar console beserta baris baru?",
                                options = listOf("printLn", "println", "echo", "log"),
                                correctAnswerIndex = 1 // println
                            ),
                            Question(
                                text = "Tipe data yang tepat untuk menyimpan teks 'Halo Dunia' adalah?",
                                options = listOf("Text", "String", "Char", "Str"),
                                correctAnswerIndex = 1 // String
                            ),
                            Question(
                                text = "Fitur Kotlin untuk mencegah aplikasi crash karena data kosong disebut?",
                                options = listOf("Null Safety", "Anti Null", "Safe Mode", "Secure Code"),
                                correctAnswerIndex = 0 // Null Safety
                            ),
                            Question(
                                text = "Manakah penulisan loop yang benar untuk angka 1 sampai 5?",
                                options = listOf("for(i in 1..5)", "for(1 to 5)", "loop(5)", "repeat(5)"),
                                correctAnswerIndex = 0 // for(i in 1..5)
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 2: MANAJEMEN WAKTU PRODUKTIF ---
        Course(
            id = "prod_01",
            title = "Manajemen Waktu Produktif",
            description = "Soft Skill & Productivity",
            instructor = "Tim Learnexus",
            level = "Semua Level",
            tags = listOf("Produktivitas", "Self Dev", "Karir"),
            modules = listOf(

                // 1. MATRIKS EISENHOWER (VIDEO)
                Module(
                    id = 201,
                    title = "Matriks Eisenhower",
                    description = "Cara membedakan hal 'Penting' vs 'Mendesak' agar tidak kewalahan.",
                    duration = "8 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=tT89OZ7ZNAM" // Link Dummy
                    )
                ),

                // 2. TEKNIK POMODORO (ARTIKEL)
                Module(
                    id = 202,
                    title = "Teknik Pomodoro",
                    description = "Metode manajemen waktu fokus 25 menit.",
                    duration = "6 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Apa itu Teknik Pomodoro?
                            
                            Diciptakan oleh Francesco Cirillo, teknik ini menggunakan timer untuk memecah pekerjaan menjadi interval fokus.
                            
                            **Langkah-langkah Praktis:**
                            1. **Pilih Tugas:** Tentukan satu tugas spesifik.
                            2. **Set Timer 25 Menit:** Matikan semua notifikasi HP/Medsos.
                            3. **Kerjakan (Deep Work):** Fokus penuh sampai alarm berbunyi.
                            4. **Istirahat 5 Menit:** Minum air, regangkan badan (jangan buka sosmed berat).
                            5. **Ulangi:** Setelah 4 sesi (4 x 25 menit), ambil istirahat panjang (15-30 menit).
                            
                            **Kenapa Efektif?**
                            Otak manusia memiliki batas fokus. Istirahat pendek menjaga otak tetap segar dan mencegah *burnout*.
                        """.trimIndent()
                    )
                ),

                // 3. UJIAN PRODUKTIVITAS (QUIZ - 10 SOAL)
                Module(
                    id = 203,
                    title = "Ujian Produktivitas",
                    description = "Tes pemahaman tentang Eisenhower & Pomodoro.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Berapa durasi fokus standar dalam satu sesi 'Pomodoro'?",
                                options = listOf("15 Menit", "25 Menit", "45 Menit", "60 Menit"),
                                correctAnswerIndex = 1 // 25 Menit
                            ),
                            Question(
                                text = "Dalam Matriks Eisenhower, tugas yang 'Penting & Mendesak' harus...",
                                options = listOf("Dikerjakan Segera (Do)", "Dijadwalkan (Decide)", "Didelegasikan (Delegate)", "Dihapus (Delete)"),
                                correctAnswerIndex = 0 // Dikerjakan Segera
                            ),
                            Question(
                                text = "Berapa lama durasi istirahat singkat (short break) antar sesi Pomodoro?",
                                options = listOf("2 Menit", "5 Menit", "15 Menit", "30 Menit"),
                                correctAnswerIndex = 1 // 5 Menit
                            ),
                            Question(
                                text = "Jika tugas itu 'Penting tapi TIDAK Mendesak' (Contoh: Olahraga, Belajar Skill Baru), apa yang harus dilakukan?",
                                options = listOf("Kerjakan sekarang juga", "Lupakan saja", "Delegasikan ke orang lain", "Jadwalkan waktu khusus"),
                                correctAnswerIndex = 3 // Jadwalkan
                            ),
                            Question(
                                text = "Apa tujuan utama dari istirahat panjang setelah 4 sesi Pomodoro?",
                                options = listOf("Tidur siang", "Menghindari kelelahan mental (Burnout)", "Makan besar", "Main game sampai puas"),
                                correctAnswerIndex = 1 // Menghindari burnout
                            ),
                            Question(
                                text = "Tugas yang 'Tidak Penting & Tidak Mendesak' (Contoh: Scroll sosmed tanpa tujuan) sebaiknya...",
                                options = listOf("Dijadwalkan", "Dikerjakan nanti", "Dihapus/Dihindari (Delete)", "Didelegasikan"),
                                correctAnswerIndex = 2 // Delete
                            ),
                            Question(
                                text = "Siapa pencipta Teknik Pomodoro?",
                                options = listOf("Elon Musk", "Dwight Eisenhower", "Francesco Cirillo", "Steve Jobs"),
                                correctAnswerIndex = 2 // Francesco Cirillo
                            ),
                            Question(
                                text = "Apa arti kata 'Pomodoro' dalam bahasa Italia?",
                                options = listOf("Waktu", "Tomat", "Fokus", "Kerja"),
                                correctAnswerIndex = 1 // Tomat (karena timer dapur bentuk tomat)
                            ),
                            Question(
                                text = "Manakah di bawah ini yang merupakan gangguan (distraksi) saat deep work?",
                                options = listOf("Musik instrumental pelan", "Notifikasi HP yang terus bunyi", "Air minum di meja", "Catatan tugas"),
                                correctAnswerIndex = 1 // Notifikasi HP
                            ),
                            Question(
                                text = "Prinsip utama produktivitas bukan 'Bekerja Lebih Keras', melainkan...",
                                options = listOf("Bekerja Lebih Cerdas (Work Smarter)", "Bekerja Lebih Lama", "Bekerja Tanpa Istirahat", "Bekerja Sendirian"),
                                correctAnswerIndex = 0 // Work Smarter
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 3: DASAR-DASAR PUBLIC SPEAKING ---
        Course(
            id = "speak_01",
            title = "Dasar-Dasar Public Speaking",
            description = "Soft Skill & Communication",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Komunikasi", "Karir", "Self Dev"),
            modules = listOf(

                // 1. MENGATASI DEMAM PANGGUNG (ARTIKEL)
                Module(
                    id = 301,
                    title = "Mengatasi Demam Panggung",
                    description = "Tips psikologis agar tidak gugup di depan umum.",
                    duration = "10 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Grogi itu Wajar!
                            
                            Bahkan pembicara profesional pun masih merasakan gugup. Bedanya, mereka tahu cara mengendalikannya.
                            
                            **Tips Mengatasi Nervous:**
                            1. **Persiapan Matang:** Kuasai materi Anda 100%. Ketidaktahuan adalah sumber ketakutan terbesar.
                            2. **Pernapasan Diafragma:** Ambil napas dalam lewat hidung, tahan 3 detik, hembuskan lewat mulut perlahan. Ini menurunkan detak jantung.
                            3. **Datang Lebih Awal:** Biasakan diri dengan ruangan dan cek *sound system* sebelum acara dimulai.
                            4. **Ubah Mindset:** Jangan berpikir "Saya harus sempurna", tapi pikirlah "Saya ingin berbagi ilmu yang bermanfaat".
                        """.trimIndent()
                    )
                ),

                // 2. STRUKTUR PIDATO EFEKTIF (VIDEO)
                Module(
                    id = 302,
                    title = "Struktur Pidato Efektif",
                    description = "Metode Pembukaan, Isi, dan Penutup yang memukau.",
                    duration = "12 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=i5mYphUoOCs" // Link Dummy TED Talk
                    )
                ),

                // 3. BAHASA TUBUH & INTONASI (ARTIKEL)
                Module(
                    id = 303,
                    title = "Bahasa Tubuh & Intonasi",
                    description = "Pentingnya kontak mata dan gestur tangan.",
                    duration = "8 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Non-Verbal Communication
                            
                            Penelitian menunjukkan bahwa audiens lebih memperhatikan *bagaimana* Anda bicara daripada *apa* yang Anda bicarakan.
                            
                            **1. Kontak Mata**
                            Jangan melihat lantai atau langit-langit. Tatap mata audiens secara bergantian selama 3-5 detik (teknik "One thought, one person").
                            
                            **2. Gestur Tangan**
                            Gunakan tangan untuk menekankan poin. Hindari memasukkan tangan ke saku (terkesan tidak percaya diri) atau melipat tangan di dada (terkesan defensif).
                            
                            **3. Intonasi Suara**
                            Hindari suara monoton. Gunakan penekanan pada kata kunci dan mainkan tempo (cepat/lambat) untuk membangun emosi.
                        """.trimIndent()
                    )
                ),

                // 4. KUIS PUBLIC SPEAKING (10 SOAL)
                Module(
                    id = 304,
                    title = "Kuis Public Speaking",
                    description = "Tes kemampuan komunikasi dan retorika.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Apa elemen terpenting dalam 30 detik pertama pidato untuk menarik perhatian?",
                                options = listOf("Kesimpulan", "Hook / Pembukaan Menarik", "Data Statistik Rumit", "Perkenalan Diri Panjang"),
                                correctAnswerIndex = 1 // Hook
                            ),
                            Question(
                                text = "Menurut penelitian Albert Mehrabian, komponen komunikasi yang paling berpengaruh adalah?",
                                options = listOf("Kata-kata (Verbal)", "Intonasi Suara (Vokal)", "Bahasa Tubuh (Visual)", "Naskah Pidato"),
                                correctAnswerIndex = 2 // Bahasa Tubuh (55%)
                            ),
                            Question(
                                text = "Apa yang sebaiknya dilakukan dengan tangan saat berbicara di depan umum?",
                                options = listOf("Dimasukkan ke saku celana", "Dilipat di depan dada", "Menggantung kaku di samping", "Bergerak natural menekankan poin"),
                                correctAnswerIndex = 3 // Bergerak natural
                            ),
                            Question(
                                text = "Cara terbaik untuk menjaga kontak mata dengan audiens besar adalah?",
                                options = listOf("Menatap satu orang saja terus menerus", "Melihat dinding belakang", "Teknik 'One thought, one person' bergantian", "Menutup mata"),
                                correctAnswerIndex = 2 // Bergantian
                            ),
                            Question(
                                text = "Apa fungsi utama dari 'Jeda' (Pause) dalam pidato?",
                                options = listOf("Karena lupa materi", "Memberi waktu audiens mencerna informasi", "Menghabiskan waktu", "Menunggu tepuk tangan"),
                                correctAnswerIndex = 1 // Mencerna informasi
                            ),
                            Question(
                                text = "Suara yang datar tanpa variasi nada disebut?",
                                options = listOf("Bariton", "Sopran", "Monoton", "Dinamis"),
                                correctAnswerIndex = 2 // Monoton
                            ),
                            Question(
                                text = "Kata-kata pengisi seperti 'Eee...', 'Ummm...', 'Anu...' disebut?",
                                options = listOf("Filler Words", "Magic Words", "Power Words", "Silent Words"),
                                correctAnswerIndex = 0 // Filler Words
                            ),
                            Question(
                                text = "Bagian penutup pidato (Closing) sebaiknya berisi...",
                                options = listOf("Permintaan maaf jika ada salah", "Materi baru", "Ringkasan dan Call to Action (Ajakan)", "Lelucon yang tidak nyambung"),
                                correctAnswerIndex = 2 // Ringkasan & CTA
                            ),
                            Question(
                                text = "Apa yang harus dilakukan jika Anda tiba-tiba lupa materi di tengah pidato?",
                                options = listOf("Lari turun panggung", "Menangis", "Tetap tenang, ambil jeda, dan cek catatan kecil", "Meminta maaf berulang-ulang"),
                                correctAnswerIndex = 2 // Tetap tenang
                            ),
                            Question(
                                text = "Siapa audiens terpenting dalam sebuah presentasi?",
                                options = listOf("Diri sendiri", "Bos perusahaan", "Pendengar/Audiens", "Panitia acara"),
                                correctAnswerIndex = 2 // Audiens (Customer Centric)
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 4: MEMAHAMI DASAR PYTHON ---
        Course(
            id = "py_01",
            title = "Memahami Dasar Python",
            description = "Tech & Data Science",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Coding", "Data", "AI"),
            modules = listOf(

                // 1. KENAPA PYTHON POPULER? (VIDEO)
                Module(
                    id = 401,
                    title = "Kenapa Python Populer?",
                    description = "Kegunaan Python di dunia AI, Web, dan Data Science.",
                    duration = "6 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=_uQrJ0TkZlc" // Link Dummy
                    )
                ),

                // 2. LIST & DICTIONARY (ARTIKEL)
                Module(
                    id = 402,
                    title = "List & Dictionary",
                    description = "Memahami struktur data dasar dalam Python.",
                    duration = "15 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Struktur Data Python
                            
                            Python memiliki cara unik menyimpan data. Dua yang paling sering dipakai adalah:
                            
                            **1. List (Daftar)**
                            Mirip Array, menggunakan kurung siku `[]`. Sifatnya berurutan.
                            ```python
                            buah = ["Apel", "Mangga", "Jeruk"]
                            print(buah[0]) # Output: Apel
                            ```
                            
                            **2. Dictionary (Kamus)**
                            Menyimpan data pasangan *Key:Value* menggunakan kurung kurawal `{}`.
                            ```python
                            siswa = {
                                "nama": "Budi",
                                "umur": 17
                            }
                            print(siswa["nama"]) # Output: Budi
                            ```
                        """.trimIndent()
                    )
                ),

                // 3. LOOPS & FUNCTIONS (ARTIKEL)
                Module(
                    id = 403,
                    title = "Loops & Functions",
                    description = "Melakukan perulangan dan membuat fungsi.",
                    duration = "20 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # 1. Indentasi itu Wajib!
                            Di Python, kita tidak pakai kurung kurawal `{}` untuk blok kode, tapi pakai **Spasi/Tab (Indentasi)**.
                            
                            # 2. Loops (Perulangan)
                            Sangat mirip bahasa Inggris.
                            ```python
                            for angka in range(5):
                                print(angka)
                            ```
                            
                            # 3. Functions (Fungsi)
                            Gunakan kata kunci `def`.
                            ```python
                            def sapa(nama):
                                print("Halo " + nama)
                                
                            sapa("Andi") 
                            ```
                        """.trimIndent()
                    )
                ),

                // 4. FINAL TEST PYTHON (QUIZ - 10 SOAL)
                Module(
                    id = 404,
                    title = "Final Test Python",
                    description = "Ujian dasar pemrograman Python.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Fungsi untuk mencetak teks ke layar di Python adalah?",
                                options = listOf("echo()", "console.log()", "print()", "System.out.print()"),
                                correctAnswerIndex = 2 // print()
                            ),
                            Question(
                                text = "Simbol yang digunakan untuk menulis komentar (tidak dieksekusi) di Python?",
                                options = listOf("//", "#", "/*", "--"),
                                correctAnswerIndex = 1 // #
                            ),
                            Question(
                                text = "Apa output dari kode: print(10 + 5)?",
                                options = listOf("105", "10 + 5", "15", "Error"),
                                correctAnswerIndex = 2 // 15
                            ),
                            Question(
                                text = "Tanda kurung apa yang digunakan untuk membuat List?",
                                options = listOf("( )", "{ }", "[ ]", "< >"),
                                correctAnswerIndex = 2 // [ ]
                            ),
                            Question(
                                text = "Tanda kurung apa yang digunakan untuk membuat Dictionary (Key:Value)?",
                                options = listOf("( )", "{ }", "[ ]", "< >"),
                                correctAnswerIndex = 1 // { }
                            ),
                            Question(
                                text = "Bagaimana penulisan nilai Boolean yang benar di Python?",
                                options = listOf("true", "True", "TRUE", "tRuE"),
                                correctAnswerIndex = 1 // True (Huruf besar)
                            ),
                            Question(
                                text = "Apa pengganti kurung kurawal {} untuk menandai blok kode di Python?",
                                options = listOf("Titik koma (;)", "Tanda petik", "Indentasi (Spasi/Tab)", "Tag HTML"),
                                correctAnswerIndex = 2 // Indentasi
                            ),
                            Question(
                                text = "Fungsi untuk meminta input dari pengguna keyboard adalah?",
                                options = listOf("get()", "scan()", "input()", "read()"),
                                correctAnswerIndex = 2 // input()
                            ),
                            Question(
                                text = "Fungsi untuk menghitung jumlah item dalam sebuah List?",
                                options = listOf("count()", "size()", "len()", "length()"),
                                correctAnswerIndex = 2 // len()
                            ),
                            Question(
                                text = "Apa ekstensi file standar untuk skrip Python?",
                                options = listOf(".py", ".pt", ".python", ".p"),
                                correctAnswerIndex = 0 // .py
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 5: APA ITU AI (ARTIFICIAL INTELLIGENCE)? ---
        Course(
            id = "ai_01",
            title = "Apa itu AI (Artificial Intelligence)?",
            description = "Tech & General Knowledge",
            instructor = "Tim Learnexus",
            level = "Menengah",
            tags = listOf("AI", "Teknologi", "Masa Depan"),
            modules = listOf(

                // 1. AI vs MACHINE LEARNING (VIDEO)
                Module(
                    id = 501,
                    title = "AI vs Machine Learning",
                    description = "Membedakan AI, Machine Learning, dan Deep Learning.",
                    duration = "10 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=ad79nYk2keg" // Link Dummy
                    )
                ),

                // 2. NEURAL NETWORKS DASAR (ARTIKEL)
                Module(
                    id = 502,
                    title = "Neural Networks Dasar",
                    description = "Bagaimana komputer meniru cara kerja otak manusia.",
                    duration = "15 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Otak Tiruan Komputer
                            
                            **Neural Network** (Jaringan Saraf Tiruan) adalah inti dari revolusi AI saat ini (Deep Learning).
                            
                            **Cara Kerjanya:**
                            Terinspirasi dari neuron otak manusia. Data masuk melalui *Input Layer*, diproses di *Hidden Layer* (tempat "keajaiban" matematika terjadi), dan keluar sebagai prediksi di *Output Layer*.
                            
                            **Contoh:**
                            Saat Anda melihat foto kucing, otak Anda langsung tahu itu kucing. Neural Network perlu melihat ribuan foto kucing dulu ("Training") untuk bisa mengenali pola kumis, telinga, dan bentuk wajahnya.
                        """.trimIndent()
                    )
                ),

                // 3. ETIKA DALAM AI (ARTIKEL)
                Module(
                    id = 503,
                    title = "Etika & Masa Depan AI",
                    description = "Bias data, Deepfakes, dan tanggung jawab manusia.",
                    duration = "8 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Pisau Bermata Dua
                            
                            AI sangat membantu, tapi juga punya risiko jika tidak diawasi.
                            
                            **1. Bias Data**
                            Jika AI dilatih menggunakan data yang rasis atau seksis, maka AI tersebut akan mengambil keputusan yang bias. Contoh: Sistem rekrutmen otomatis yang menolak pelamar wanita karena data masa lalu didominasi pria.
                            
                            **2. Deepfakes**
                            Teknologi memalsukan wajah dan suara orang lain. Ini berbahaya untuk penyebaran hoaks dan penipuan.
                            
                            **3. Job Displacement**
                            AI akan menggantikan pekerjaan repetitif, tapi juga menciptakan lapangan kerja baru di bidang data dan maintenance AI.
                        """.trimIndent()
                    )
                ),

                // 4. KUIS WAWASAN AI (10 SOAL)
                Module(
                    id = 504,
                    title = "Kuis Wawasan AI",
                    description = "Uji pengetahuan tentang kecerdasan buatan.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Siapa tokoh yang dikenal sebagai 'Bapak Komputer' dan pencetus konsep kecerdasan mesin?",
                                options = listOf("Steve Jobs", "Bill Gates", "Alan Turing", "Elon Musk"),
                                correctAnswerIndex = 2 // Alan Turing
                            ),
                            Question(
                                text = "Apa nama tes untuk menentukan apakah mesin mampu berpikir seperti manusia?",
                                options = listOf("IQ Test", "Turing Test", "Speed Test", "Logic Test"),
                                correctAnswerIndex = 1 // Turing Test
                            ),
                            Question(
                                text = "ChatGPT termasuk dalam kategori AI jenis apa?",
                                options = listOf("Robotics", "Computer Vision", "Generative AI (LLM)", "Expert System"),
                                correctAnswerIndex = 2 // Generative AI
                            ),
                            Question(
                                text = "Proses 'mengajarkan' mesin dengan memberinya banyak data disebut?",
                                options = listOf("Coding", "Training", "Deploying", "Debugging"),
                                correctAnswerIndex = 1 // Training
                            ),
                            Question(
                                text = "Cabang AI yang membuat komputer bisa 'melihat' dan memahami gambar/video adalah?",
                                options = listOf("NLP", "Computer Vision", "Speech Recognition", "Data Mining"),
                                correctAnswerIndex = 1 // Computer Vision
                            ),
                            Question(
                                text = "Deep Learning terinspirasi oleh struktur biologis apa?",
                                options = listOf("DNA", "Sel Darah", "Jaringan Saraf Otak (Neuron)", "Tulang Belakang"),
                                correctAnswerIndex = 2 // Neuron
                            ),
                            Question(
                                text = "Apa risiko utama jika data yang digunakan melatih AI tidak seimbang?",
                                options = listOf("Komputer jadi panas", "Bias Algoritma", "Listrik boros", "Internet lambat"),
                                correctAnswerIndex = 1 // Bias
                            ),
                            Question(
                                text = "Siri, Google Assistant, dan Alexa adalah contoh dari?",
                                options = listOf("Narrow AI (AI Sempit/Khusus)", "General AI (AGI)", "Super AI", "Dumb AI"),
                                correctAnswerIndex = 0 // Narrow AI
                            ),
                            Question(
                                text = "Teknologi AI yang memalsukan wajah/suara seseorang dalam video disebut?",
                                options = listOf("Photoshop", "CGI", "Deepfake", "Filter"),
                                correctAnswerIndex = 2 // Deepfake
                            ),
                            Question(
                                text = "NLP adalah singkatan dari?",
                                options = listOf("Natural Language Processing", "Neural Learning Protocol", "New Logic Programming", "No Laptop Problem"),
                                correctAnswerIndex = 0 // Natural Language Processing
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 6: COPYWRITING UNTUK MEDIA SOSIAL ---
        Course(
            id = "copy_01",
            title = "Copywriting untuk Media Sosial",
            description = "Marketing & Business",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Marketing", "Bisnis", "Writing"),
            modules = listOf(

                // 1. FORMULA AIDA (VIDEO)
                Module(
                    id = 601,
                    title = "Formula AIDA",
                    description = "Attention, Interest, Desire, Action. Rumus wajib copywriter.",
                    duration = "8 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=17jK2W2gq6c" // Link Dummy
                    )
                ),

                // 2. MEMBUAT HEADLINE MEMIKAT (ARTIKEL)
                Module(
                    id = 602,
                    title = "Membuat Headline Memikat",
                    description = "Teknik menulis judul yang 'Clickable' tapi bukan Clickbait.",
                    duration = "12 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # 80% Orang Hanya Membaca Judul
                            
                            Jika judul (headline) Anda membosankan, isi konten tidak akan dibaca.
                            
                            **3 Formula Headline Terbukti:**
                            
                            **1. The 'How To' (Cara Melakukan)**
                            * "Cara Menurunkan Berat Badan Tanpa Diet Ketat"
                            * "Bagaimana Saya Mendapat 1000 Follower dalam Seminggu"
                            
                            **2. The Listicle (Angka)**
                            Otak manusia suka angka karena terukur.
                            * "7 Kesalahan Fatal Pengusaha Pemula"
                            * "5 Tools Wajib untuk Desainer Grafis"
                            
                            **3. The Fear of Missing Out (FOMO)**
                            * "Jangan Beli HP Ini Sebelum Baca Reviewnya!"
                            * "Hati-hati! Ini Tanda Akun Anda Sedang Diretas"
                        """.trimIndent()
                    )
                ),

                // 3. UJIAN COPYWRITING (10 SOAL)
                Module(
                    id = 603,
                    title = "Ujian Copywriting",
                    description = "Tes kemampuan menulis naskah iklan.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "Huruf 'A' pertama dalam formula AIDA singkatan dari?",
                                options = listOf("Action", "Attention", "Appeal", "Awareness"),
                                correctAnswerIndex = 1 // Attention
                            ),
                            Question(
                                text = "Bagian teks iklan yang mengajak pembaca melakukan sesuatu (Beli/Daftar) disebut?",
                                options = listOf("Headline", "Body Copy", "Call to Action (CTA)", "Footer"),
                                correctAnswerIndex = 2 // CTA
                            ),
                            Question(
                                text = "Judul yang berlebihan dan menipu isi konten disebut?",
                                options = listOf("Clickbait", "Hook", "Headline", "Lead"),
                                correctAnswerIndex = 0 // Clickbait
                            ),
                            Question(
                                text = "Istilah 'Copy' dalam dunia marketing berarti?",
                                options = listOf("Menyalin teks", "Naskah iklan/promosi", "Mesin fotokopi", "Hak cipta"),
                                correctAnswerIndex = 1 // Naskah iklan
                            ),
                            Question(
                                text = "Profil detail tentang siapa target audiens ideal kita disebut?",
                                options = listOf("Buyer Persona", "Follower", "Subscriber", "Viewer"),
                                correctAnswerIndex = 0 // Buyer Persona
                            ),
                            Question(
                                text = "'Baterai tahan 24 jam' adalah contoh dari...",
                                options = listOf("Benefit (Manfaat)", "Feature (Fitur)", "Diskon", "Garansi"),
                                correctAnswerIndex = 1 // Feature
                            ),
                            Question(
                                text = "'Anda tidak perlu bawa powerbank lagi seharian' adalah contoh dari...",
                                options = listOf("Benefit (Manfaat)", "Feature (Fitur)", "Spesifikasi", "Teknis"),
                                correctAnswerIndex = 0 // Benefit
                            ),
                            Question(
                                text = "Teknik menggunakan testimoni orang lain untuk meyakinkan pembeli disebut?",
                                options = listOf("Social Proof", "Social Media", "Social Distancing", "Social Life"),
                                correctAnswerIndex = 0 // Social Proof
                            ),
                            Question(
                                text = "Prinsip 'Scarcity' (Kelangkaan) bertujuan untuk?",
                                options = listOf("Menakuti pembeli", "Mendorong tindakan cepat", "Menipu stok", "Menaikkan harga"),
                                correctAnswerIndex = 1 // Mendorong tindakan cepat
                            ),
                            Question(
                                text = "Gaya bahasa terbaik untuk media sosial biasanya adalah...",
                                options = listOf("Kaku dan Formal", "Akademis", "Conversational (Seperti ngobrol)", "Puitis"),
                                correctAnswerIndex = 2 // Conversational
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 7: KONSEP DASAR DIGITAL MARKETING ---
        Course(
            id = "dm_01",
            title = "Konsep Dasar Digital Marketing",
            description = "Marketing & Business",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Marketing", "Bisnis", "SEO"),
            modules = listOf(

                // 1. SEO VS SEM (ARTIKEL)
                Module(
                    id = 701,
                    title = "SEO vs SEM: Apa Bedanya?",
                    description = "Memahami trafik organik (gratis) dan berbayar (iklan).",
                    duration = "10 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Dua Kunci Pencarian Google
                            
                            Saat Anda mencari sesuatu di Google, ada dua jenis hasil yang muncul:
                            
                            **1. SEO (Search Engine Optimization)**
                            * **Sifat:** Gratis (Organik).
                            * **Cara Kerja:** Mengoptimalkan konten website agar disukai algoritma Google (kata kunci relevan, website cepat).
                            * **Jangka Waktu:** Lama (butuh 3-6 bulan untuk terlihat hasilnya), tapi awet.
                            
                            **2. SEM (Search Engine Marketing)**
                            * **Sifat:** Berbayar (Iklan/Ads).
                            * **Cara Kerja:** Membayar Google untuk menaruh website kita di paling atas (biasanya ada label 'Sponsored' atau 'Iklan').
                            * **Jangka Waktu:** Instan (langsung muncul saat itu juga), tapi hilang saat saldo iklan habis.
                        """.trimIndent()
                    )
                ),

                // 2. SOCIAL MEDIA MARKETING (VIDEO)
                Module(
                    id = 702,
                    title = "Strategi Media Sosial",
                    description = "Memilih platform yang tepat: Instagram, TikTok, atau LinkedIn?",
                    duration = "10 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=S3X-jM2S184" // Link Dummy
                    )
                ),

                // 3. KUIS DIGITAL MARKETING (10 SOAL)
                Module(
                    id = 703,
                    title = "Kuis Digital Marketing",
                    description = "Tes pemahaman dasar pemasaran digital.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "SEO adalah singkatan dari?",
                                options = listOf("Search Engine Operation", "Search Engine Optimization", "Social Engine Organization", "Sales Engine Online"),
                                correctAnswerIndex = 1 // Optimization
                            ),
                            Question(
                                text = "Iklan berbayar yang muncul di hasil pencarian Google disebut?",
                                options = listOf("SEO", "SEM", "SMM", "Affiliate"),
                                correctAnswerIndex = 1 // SEM
                            ),
                            Question(
                                text = "Platform terbaik untuk pemasaran B2B (Business to Business) dan profesional adalah?",
                                options = listOf("TikTok", "Instagram", "LinkedIn", "Snapchat"),
                                correctAnswerIndex = 2 // LinkedIn
                            ),
                            Question(
                                text = "Trafik website yang didapatkan secara gratis tanpa membayar iklan disebut?",
                                options = listOf("Trafik Berbayar", "Trafik Organik", "Trafik Ilegal", "Trafik Bot"),
                                correctAnswerIndex = 1 // Organik
                            ),
                            Question(
                                text = "Strategi bekerjasama dengan orang populer untuk mempromosikan produk disebut?",
                                options = listOf("Email Marketing", "Influencer Marketing", "SEO Marketing", "Telemarketing"),
                                correctAnswerIndex = 1 // Influencer Marketing
                            ),
                            Question(
                                text = "Apa kepanjangan dari ROI dalam bisnis?",
                                options = listOf("Rate of Interest", "Return on Investment", "Risk of Internet", "Range of Income"),
                                correctAnswerIndex = 1 // Return on Investment
                            ),
                            Question(
                                text = "Metode membandingkan dua versi iklan (Versi A dan B) untuk melihat mana yang lebih baik disebut?",
                                options = listOf("A/B Testing", "Alpha Testing", "Beta Testing", "Copy Testing"),
                                correctAnswerIndex = 0 // A/B Testing
                            ),
                            Question(
                                text = "Kelompok orang spesifik yang menjadi sasaran penjualan produk kita disebut?",
                                options = listOf("Target Audience", "Followers", "Subscribers", "Citizens"),
                                correctAnswerIndex = 0 // Target Audience
                            ),
                            Question(
                                text = "Kata atau frasa yang diketik pengguna di Google saat mencari sesuatu disebut?",
                                options = listOf("Password", "Keyword (Kata Kunci)", "Hashtag", "Username"),
                                correctAnswerIndex = 1 // Keyword
                            ),
                            Question(
                                text = "Tujuan utama dari Content Marketing adalah?",
                                options = listOf("Langsung jualan secara agresif", "Memberi nilai/edukasi untuk membangun kepercayaan", "Membeli follower", "Spam komentar"),
                                correctAnswerIndex = 1 // Memberi nilai
                            )
                        )
                    )
                )
            )
        )
        ,
        // --- COURSE 8: MENGENAL UI/UX DESIGN ---
        Course(
            id = "design_01",
            title = "Mengenal UI/UX Design",
            description = "Design & Creative",
            instructor = "Tim Learnexus",
            level = "Pemula",
            tags = listOf("Desain", "UI/UX", "Kreatif"),
            modules = listOf(

                // 1. UI VS UX (VIDEO)
                Module(
                    id = 801,
                    title = "UI vs UX: Apa Bedanya?",
                    description = "UI adalah tampilan (Wajah), UX adalah pengalaman (Rasa).",
                    duration = "7 Menit",
                    type = ContentType.VIDEO,
                    content = VideoContent(
                        videoUrl = "https://www.youtube.com/watch?v=5CxXnyrYs6M" // Link Dummy
                    )
                ),

                // 2. WIREFRAMING & PROTOTYPING (ARTIKEL)
                Module(
                    id = 802,
                    title = "Wireframing & Prototyping",
                    description = "Tahapan penting sebelum mendesain tampilan akhir.",
                    duration = "15 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Jangan Langsung Buka Laptop!
                            
                            Desainer profesional tidak langsung mewarnai tombol. Ada tahapannya:
                            
                            **1. Wireframe (Low-Fidelity)**
                            Ini adalah kerangka dasar. Biasanya berupa coretan hitam putih di kertas atau Figma. Tujuannya untuk menentukan tataletak (layout) tanpa terganggu oleh pemilihan warna.
                            
                            **2. Mockup (High-Fidelity)**
                            Desain visual yang sudah jadi. Sudah ada warna, foto, tipografi, dan ikon yang rapi. Ini yang dilihat oleh user.
                            
                            **3. Prototype**
                            Menghubungkan layar satu ke layar lain agar bisa diklik (interaktif). Ini digunakan untuk simulasi alur aplikasi.
                        """.trimIndent()
                    )
                ),

                // 3. PRINSIP DESAIN VISUAL (ARTIKEL)
                Module(
                    id = 803,
                    title = "Prinsip Desain Visual",
                    description = "Kontras, Hierarki, dan Ruang Kosong (White Space).",
                    duration = "10 Menit",
                    type = ContentType.ARTICLE,
                    content = ArticleContent(
                        body = """
                            # Agar Desain Enak Dilihat
                            
                            **1. Hierarchy (Hierarki)**
                            Mata manusia butuh panduan mana yang harus dilihat duluan. Judul harus lebih besar dari sub-judul. Tombol "Beli" harus lebih mencolok dari tombol "Batal".
                            
                            **2. White Space (Ruang Kosong)**
                            Jangan memadatkan semua elemen. Beri jarak antar elemen agar desain bisa "bernapas" dan terlihat elegan.
                            
                            **3. Contrast (Kontras)**
                            Pastikan tulisan terbaca jelas. Jangan gunakan teks abu-abu muda di atas latar belakang putih. Gunakan warna kontras untuk elemen penting.
                        """.trimIndent()
                    )
                ),

                // 4. UJIAN AKHIR DESAIN (10 SOAL)
                Module(
                    id = 804,
                    title = "Ujian Akhir Desain",
                    description = "Tes pemahaman dasar UI dan UX.",
                    duration = "10 Menit",
                    type = ContentType.QUIZ,
                    content = QuizContent(
                        questions = listOf(
                            Question(
                                text = "UI adalah singkatan dari?",
                                options = listOf("User Interest", "User Interface", "User Interaction", "User Internet"),
                                correctAnswerIndex = 1 // User Interface
                            ),
                            Question(
                                text = "UX adalah singkatan dari?",
                                options = listOf("User Xylophone", "User Experience", "User Expert", "User Example"),
                                correctAnswerIndex = 1 // User Experience
                            ),
                            Question(
                                text = "Sketsa kasar hitam putih untuk menyusun tata letak disebut?",
                                options = listOf("Wireframe", "Mockup", "Prototype", "Final Design"),
                                correctAnswerIndex = 0 // Wireframe
                            ),
                            Question(
                                text = "Software desain UI/UX paling populer saat ini yang berbasis web adalah?",
                                options = listOf("Adobe Photoshop", "Microsoft Paint", "Figma", "Canva"),
                                correctAnswerIndex = 2 // Figma
                            ),
                            Question(
                                text = "Apa fungsi utama dari White Space (Ruang Kosong)?",
                                options = listOf("Membuang tempat", "Membuat desain terlihat kosong", "Memberi jeda visual agar tidak sumpek", "Kesalahan desainer"),
                                correctAnswerIndex = 2 // Memberi jeda
                            ),
                            Question(
                                text = "Model desain yang sudah bisa diklik dan interaktif untuk simulasi disebut?",
                                options = listOf("Wireframe", "JPG", "Prototype", "Screenshot"),
                                correctAnswerIndex = 2 // Prototype
                            ),
                            Question(
                                text = "Manakah analogi yang tepat untuk UI dan UX?",
                                options = listOf("UI itu Kode, UX itu Gambar", "UI itu Tubuh, UX itu Baju", "UI itu Tampilan (Wajah), UX itu Rasa (Pengalaman)", "UI dan UX itu sama saja"),
                                correctAnswerIndex = 2 // Tampilan vs Rasa
                            ),
                            Question(
                                text = "Ukuran teks judul yang lebih besar daripada teks isi adalah contoh penerapan prinsip...",
                                options = listOf("Kontras", "Hierarki Visual", "Repetisi", "Warna"),
                                correctAnswerIndex = 1 // Hierarki
                            ),
                            Question(
                                text = "Warna teks yang sulit dibaca karena mirip dengan warna background melanggar prinsip...",
                                options = listOf("Kontras", "Keseimbangan", "White Space", "Tipografi"),
                                correctAnswerIndex = 0 // Kontras
                            ),
                            Question(
                                text = "Siapa yang harus menjadi fokus utama saat mendesain produk?",
                                options = listOf("Klien", "Diri Sendiri", "Pengguna (User)", "Programmer"),
                                correctAnswerIndex = 2 // User
                            )
                        )
                    )
                )
            )
        )
    )
}