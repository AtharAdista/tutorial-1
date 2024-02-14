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
