# Tutorial

Nama : Shaquille Athar Adista </br>
Kelas : C </br>
NPM : 2206081875

<details>
<summary>Tutorial 1</summary>

### Reflection 1

Setelah mencoba lab satu saya menyadari bahwa pemahaman saya menggunakan Spring Boot masih kurang, dikarenakan saya baru pertama kali menggunakan Spring Boot. </br>

Saya sudah mencoba untuk menerapkan clean code pada code yang telah saya buat, namun kemungkinan penerapan clean code saya terapkan masih memiliki kekurangan.

Clean code yang sudah diterapkan:
- Nama variabel yang jelas dan self-explained.
- Nama fungsi yang jelas dan self-explained.
- Fungsi yang hanya melakukan satu tugas (tugas yang sederhana).
- Error handling, saya sudah menerapkan beberapa error handling pada kode saya.
- Saya sudah menerapkan OOP dan juga interface agar kode yang saya buat lebih terstruktur dan rapi.

Secure code yang sudah saya terapkan (Saya kurang yakin apakah ini termasuk secure code):
- Menerapkan post untuk create product, edit product, dan delete product

Kekurangan code saya terdapat pada fungsi edit dan delete yang masih menggunakan metode post, seharusnya edit bisa menggunakan metode put dan delete bisa menggunakan metode delete.


### Reflection 2



1. Unit test memiliki fungsi untuk melakukan pengecekan apakah kode kita berjalan secara semestinya. Unit test menurut saya sangat berguna dikarenakan jika kita mengganti-ganti kode, kita bisa melakukan pengecekan secara otomatis dan cepat.  Kita bisa mengutamakan membuat unit test yang memeriksa hal-hal yang signifikan dalam kode, setelah hal signifikan selesai diperiksa barulah kita fokus membuat unit test yang fokus ke hal-hal detail. Menurut saya untuk membuat unit test yang bisa cover 100% kode kita sangatlah sulit dan membuang-buang banyak waktu . Selain itu, walaupun kita berhasil membuat unit test yang sudah cover 100% keseluruhan kode, tidak menjamin bahwa kode tersebut akan bebas dari bug karena mungkin saja ada edge case yang kita lewatkan. Setelah saya baca dari google, unit test yang optimal adalah unit test yang bisa cover 70-80% code kita.


2. Dikarenakan fuctional testnya memiliki setup prosedur dan variable yang sama maka akan menyebabkan code menjadi kurang clean dikarenakan adanya redundant dan duplikasi, seharusnya kita bisa menyatukan kode-kode tersebut dalam satu file atau melakukan extend class dari class yang ada pada CreateProductFunctionalTest.java agar tidak terjadi redundant dan duplikasi. Selain itu, juga ada kemungkinan masalah pemeliharaan dikarenakan kode yang mirip dapat membuat kita mengalami kebingungan.
</details>

<details>
    <summary>Tutorial 2</summary>

### Reflection

Code coverage

![image](https://github.com/AtharAdista/tutorial-1/assets/117746860/2f93209a-2e7c-4935-9456-7b86b46f53d9)


Ada beberapa masalah kode yang dideteksi oleh sonarcloud, berikut kode yang saya perbaiki demi meningkatkan kualitas kode saya
- Dalam penamaan fungsi harus menggunakan camel case, namun ada beberapa fungsi saya yang tidak menerapkan hal ini. Setelah menerima code quality dari sonarcloud akhirnya saya memperbaiki masalah ini (misalnya mengganti method HomePage menjadi homePage)
- Consistency (Add description to table tag). Masalah kedua dari kode saya yang dilaporkan oleh sonarcloud adalah harus memakai description jika menggunakan tag `<table>`. Untuk memperbaiki masalah ini, saya menambahkan deskripsi ke dalam table saya.
- Saya juga memperbaiki beberapa penamaan file HTML saya, dikarenakan ternyata jika deploy di koyeb, maka file html akan menjadi case sensitive.

Strategi saya untuk memperbaiki kualitas kode saya adalah dengan memanfaatkan sonarcloud, yang mana dengan menggunakan sonarcloud, maka kita akan tahu beberapa masalah pada kode kita dan cara mengimprove kode tersebut.

Menurut saya, kode saya sudah menerapkan CI/CD, namun mungkin penggunaannya masih belum maksimal, alasan saya mengatakan mengapa kode saya sudah menerapkan CI/CD adalah dikarenakan kode saya sudah menggunakan gradle yang mana membuat proyek java tanpa perlu compile secara manual satu persatu. Selain itu, saya sudah menerapkan penggunaan github action (ci.yml) untuk melakukan pengetesan kode secara otomatis setiap melakukan push ataupun pull ke repo github dan saya juga menggunakan beberapa tool yang dapat mengecek kualitas kode (scorecard.yml dan sonarcloud.yml) secara otomatis ketika saya melakukan push ataupun pull ke repo github. Dalam melakukan deploy, proses deploy akan terjadi secara otomatis setiap saya melakukan push ataupun pull ke repo github saya.
</details>

<details>
    <summary>
        Tutorial 3
    </summary>

1. Pada proyek ini, saya menerapkan SOLID principle
   - SRP (Single responsiblity principle), prinsip ini memiliki arti bahwa sebuah class cuman boleh melakukan satu pekerjaan saja. Contoh penerapan SRP dalam proyek saya adalah memisahkan antara ProductController dengan CarController dalam file yang berbeda karena tugas mereka yang berbeda.
   - OCP (Open-closed principle), prinsip ini memiliki arti bahwa sistem perangkat lunak (kelas, modul, fungsi, dll) harus terbuka untuk perluasan tetapi tertutup untuk modifikasi.Contoh penerapannya pada proyek ini adalah dengan membuat class untuk interface product, sehingga jika kita nantinya ingin membuat product baru, kita bisa implement interface product yang sudah tersedia
   - LSP (Liskov Substitution Principle), prinsip ini memiliki arti bahwa objek dari suatu kelas harus dapat diganti dengan objek dari kelas induknya tanpa mengganggu kebenaran program. Pada proyek ini diterapkan saat kita mengganti kelas `CarServiceImpl` dengan `CarService` pada `CarController` maka tidak akan terjadi error.
   - ISP (Interface Segregation Principle), prinsip ini memiliki arti bahwa interface yang besar dapat dipecah-pecah ke interface yang lebih kecil sehingga tidak perlu memaksa membuat suatu method yang tidak relevan bagi kelas tersebut. Dalam proyek ini sudah diterapkan dengan membuat CarService yang hanya berfokus untuk car saja, sehingga nantinya kita bisa membuat method yang spesifik untuk car saja.
   - DIP (Dependency Inversion Principle), prinsip ini memiliki arti bahwa modul tingkat tinggi tidak boleh tergantung pada modul tingkat rendah, tetapi harus bergantung pada abstraksi. Pada proyek ini diterapkan dengan memanggil kelas `CarService` bukan kelas `CarServiceImpl` pada `CarController`


2. Keuntungan penerapan SOLID principle
   - Kode akan lebih clean dan terstruktur, penerapan SRP membuat saya memisahkan CarController dan ProductController dalam file yang berbeda sehingga akan membuat kode menjadi lebih mudah dipahami, penerapan ISP juga membuat interface menjadi lebih spesifik contohnya pada penerapan `CarService`.
   - Kode akan fleksibel, penerapan OCP membuat kita dapat membuat fitur baru tanpa memodifikasi kode yang sudah ada contohnya seperti penerapan interface pada `CarService`, dengan adanya `CarService` nantinya kita dapat membuat suatu fitur baru tanpa perlu merubah kelas yang sudah ada, penerapan LSP membuat kita dapat mengganti suatu kelas dengan kelas turunannya tanpa menyebabkan kesalahan. Selain itu, dengan penerapan DIP kita dapat mengurangi ketergantungan antar kode, jadinya kita dapat dengan mudah menerapkan fitur baru tanpa menyebabkan adanya kesalahan.


3. Kekurangan jika tidak menerapkan SOLID princile
   - Kode akan sulit di maintain, contoh dari kasus ini adalah ketika kita tidak menerapkan pemisahan antara CarController dan ProductController dalam file yang berbeda. Jika kita menyatukan dua kelas tersebut dalam file yang sama, maka nantinya kita akan mengalami kebingungan karena dalam satu file terdapat kelas berbeda yang tujuannya berbeda.
   - Kode akan sulit di pahami, contoh dari kasus ini adalah ketika tidak menerapkan DIP, maka kode akan memiliki ketergantungan yang tinggi, selain itu tidak menerapkan SRP akan membuat kode sulit dipahami karena ada kemungkinan bahwa ada class yang memiliki tugas yang sangat banyak.
   - Kode kurang fleksibel, dengan tidak menerapkan prinsip LSP dan OCP maka kode kita akan rentan terhadap perubahan dan sulit diperluas dikarenakan adanya potensi error.
   - Kode akan kurang relevan, dengan tidak menerapkan prinsip ISP maka kode kita akan memiliki banyak sekali method-method yang tidak relevan.
</details>