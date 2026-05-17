# AUTOMATION TESTING (Page Object Model)

## 10.1 Tujuan Pembelajaran

### Tujuan:

- Mahasiswa mampu memahami konsep Page Object Model (POM).
- Mahasiswa mampu mengimplementasikan POM dalam pengujian.

---

# 10.2 Dasar Teori

Page Object Model (POM) adalah salah satu design pattern yang dapat diterapkan pada skrip pengujian perangkat lunak. POM bertujuan untuk meningkatkan keterbacaan, perawatan, dan ketahanan terhadap perubahan pada skrip pengujian.

Dalam POM, setiap halaman web dianggap sebagai objek terpisah, dan setiap objek halaman memiliki metode-metode yang merepresentasikan perilaku dan interaksi dengan elemen-elemen di halaman tersebut.

Pendekatan ini membantu memisahkan logika bisnis dan akses elemen dari implementasi pengujian, memungkinkan pengembang untuk membuat dan memelihara skrip pengujian dengan lebih mudah.

Metode atau pengujian kelas dapat menjadi rumit dan sulit dikelola karena kurangnya struktur yang jelas. Ketika elemen pencarian muncul beberapa kali di method atau kelas berbeda, hal ini dapat menyebabkan redundansi dan meningkatkan kesulitan dalam mengelola skrip pengujian.

Dalam Page Object Model, elemen-elemen dan perilaku yang terkait dengan suatu halaman diisolasi dalam kelas Page Object yang khusus. Hal ini memberikan beberapa keuntungan:

- Mengurangi duplikasi kode.
- Memudahkan maintenance.
- Perubahan halaman web cukup diperbaiki di satu tempat.
- Struktur pengujian menjadi lebih modular dan mudah dipahami.
- Meningkatkan efisiensi dalam pengembangan perangkat lunak.

Penerapan POM membantu tim pengembang mengoptimalkan proses testing dan mengurangi kompleksitas pengelolaan automation testing.

---

## Penjelasan Gambar Diagram POM

Diagram pada modul menunjukkan hubungan antar komponen dalam penerapan Page Object Model.

### Isi Diagram:

- **Test Cases**
  - Berisi skenario pengujian.
  - Contoh: `Login Test Case`.
  - Test case hanya fokus pada alur pengujian.

- **Page Object**
  - Bertindak sebagai penghubung antara test case dan halaman web.
  - Menyimpan method dan locator untuk berinteraksi dengan halaman.

- **Inventory Page**
  - Merepresentasikan halaman inventory pada aplikasi.
  - Menyimpan elemen-elemen yang digunakan pada pengujian.

- **Web Element**
  - Elemen-elemen UI seperti button, textbox, input field, dan label.

### Kesimpulan Diagram:

Diagram menjelaskan bahwa:

- Test case tidak langsung mengakses elemen web.
- Semua interaksi dilakukan melalui class Page Object.
- Pendekatan ini membuat kode lebih rapi, reusable, dan mudah dipelihara.

---

# 10.3 Latihan

Pada kasus pengujian Bing Search, terdapat 3 class yang dapat diimplementasikan:

## 1. Search Page Class

Class ini berisi:

- Atribut elemen search bar.
- Atribut tombol submit.
- Method untuk melakukan pencarian.

## 2. Search Result Class

Class ini berisi:

- Atribut title halaman hasil pencarian.
- Method untuk mengambil hasil pencarian.

## 3. Search Test Class

Class ini berisi:

- Method pengujian.
- Assertion.
- Pemanggilan method dari page object.

---

## Langkah Latihan

### 1. Membuat Search Page Class

Buat class yang memiliki:

- Locator search bar.
- Locator tombol submit.
- Method input pencarian.
- Method klik submit.

### 2. Membuat Result Page Class

Buat class yang dapat:

- Mengakses title halaman.
- Mengambil hasil pencarian.

### 3. Membuat Test Class

Buat class pengujian yang:

- Membuat object dari search page.
- Memanggil method pencarian.
- Melakukan assertion.

---

# 10.4 Tugas

## Tugas 1

Ubah kode test login pada aplikasi SauceDemo menjadi bentuk POM.

## Tugas 2

Explorasi dan implementasikan:

### Centralized Locators

Buat class khusus yang berisi semua locator.

### Tujuan:

- Memudahkan perubahan locator.
- Mengurangi duplikasi locator.
- Maintenance lebih mudah.

---

## Tugas 3

Explorasi dan implementasikan class `BasePage`.

### Isi BasePage:

Method umum seperti:

- `click()`
- `inputText()`
- `waitForElement()`

### Tujuan BasePage:

- Mengurangi code duplication.
- Menyatukan fungsi umum.
- Mempermudah inheritance.

---

# Penjelasan Gambar BasePage

Gambar pada modul menunjukkan contoh implementasi `BasePage`.

## Fungsi pada Contoh:

### waitForElementVisible(By by)

Method ini digunakan untuk:

- Menunggu elemen muncul.
- Memastikan elemen dapat digunakan.
- Menghindari error karena elemen belum siap.

Biasanya menggunakan:

- `ExpectedConditions`
- `WebDriverWait`

---

### click(By by)

Method ini digunakan untuk:

- Menunggu elemen visible.
- Setelah visible, elemen langsung di-click.

Konsep ini membuat semua proses click memiliki mekanisme wait otomatis.

---

# Kesimpulan

Page Object Model (POM) adalah pendekatan automation testing yang membantu:

- Membuat kode lebih modular.
- Mengurangi duplikasi.
- Mempermudah maintenance.
- Memisahkan logic testing dan akses elemen.
- Meningkatkan readability automation testing.

POM sangat cocok digunakan pada project automation testing skala kecil maupun besar karena struktur kode menjadi lebih rapi dan scalable.

