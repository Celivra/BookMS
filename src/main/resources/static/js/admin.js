//打开或关闭修改密码窗口
function toggleChangePassword(){
    const cPasswdForm = document.getElementById("changePassword");
    cPasswdForm.style.display = cPasswdForm.style.display === 'none'? 'block':'none';
}
//开启添加书籍的界面
function toggleAddBook(){
    /*=================================将控件显示出来============================================*/
    document.getElementById('addBookOverlay').style.display = 'flex';
    document.getElementById('addbookh2').style.display = 'block';
    document.getElementById('addBookForm').style.display = 'block';
    document.getElementById('changebookh2').style.display = 'none';
    document.getElementById('changeBookForm').style.display = 'none';
    /*================================调整显示方式结束===========================================*/
}
//开启修改图书的界面
function toggleChangeBook(bookData){
    /*==============================为每一个控件添加数据===========================================*/
    document.getElementById('changebook-id').value= bookData.id;
    document.getElementById('changebook-isbn').value= bookData.isbn;
    document.getElementById('changebook-name').value= bookData.title;
    document.getElementById('changebook-author').value = bookData.author;
    document.getElementById('changebook-type').value = bookData.type;
    document.getElementById('changebook-publisher').value = bookData.publisher;
    document.getElementById('changebook-publisheddate').value = bookData.publisheddate;
    document.getElementById('changebook-number').value = bookData.number;
    document.getElementById('changebook-desc').value = bookData.desc;
    /*==================================添加数据结束=============================================*/

    /*=================================将控件显示出来============================================*/
    document.getElementById('addBookOverlay').style.display = 'flex';
    document.getElementById('addbookh2').style.display = 'none';
    document.getElementById('addBookForm').style.display = 'none';
    document.getElementById('changebookh2').style.display = 'block';
    document.getElementById('changeBookForm').style.display = 'block';
    /*================================调整显示方式结束===========================================*/
}
//将确认删除书籍窗口显示出来
function toggleDeleteBook(id){
    document.getElementById('deleteBook').style.display = 'flex';
    document.getElementById('deleteBookId').value = id;
}
//将更新用户窗口显示出来
function toggleUpdateUser(userData){

    /*==============================为每一个控件添加数据===========================================*/
    document.getElementById('updateUser-id').value = userData.id;
    document.getElementById('updateUser-username').value = userData.username;
    document.getElementById('updateUser-phone').value = userData.phone;
    document.getElementById('updateUser-email').value = userData.email;
    document.getElementById('updateUser-power').checked = (userData.power ==='10')
    document.getElementById('UserOverlay').style.display = 'flex'
    /*==================================添加数据结束=============================================*/

}
//将删除用户窗口显示出来
function toggleDeleteUser(id){
    document.getElementById('deleteUser').style.display = 'flex';
    document.getElementById('deleteUserId').value = id;
}
//当点击书籍卡片时将数据获取到，并调用开启窗口函数
function ClickBookCard(el){
    const card = el.closest('.book-card');

    /*===============================获取各个属性的值============================================*/
    const isbn = card.dataset.isbn
    const id = card.dataset.id
    const title = card.dataset.title;
    const author = card.dataset.author;
    const type = card.dataset.type;
    const publisher = card.dataset.publisher;
    const publisheddate = card.dataset.publisheddate;
    const number = card.dataset.number;
    const desc = card.dataset.desc;
    /*=================================获取值结束==============================================*/

    toggleChangeBook({id, isbn,title, author, type, publisher, publisheddate, number, desc});
}

//当点击删除书籍按钮
function ClickDeleteBook(el){
    const card = el.closest('.book-card');
    const id = card.dataset.id;
    toggleDeleteBook(id);
}
//当点击删除用户按钮
function ClickDeleteUser(el){
    const card = el.closest('.user-card');
    const id = card.dataset.id;
    toggleDeleteUser(id);
}
//当点击更新用户按钮
function ClickUser(el){
    const user = el.closest('.user-card')
    /*===============================获取各个属性的值============================================*/
    const id = user.dataset.id
    const username = user.dataset.username
    const phone =user.dataset.phone
    const email = user.dataset.email
    const power = user.dataset.power
    /*=================================获取值结束==============================================*/

    toggleUpdateUser({id, username, phone, email, power})

}

//显示工单窗口
function viewTicket(e){
    /*==============================为每一个控件添加数据===========================================*/
    document.getElementById("showTicket-id").value = e.dataset.id;
    document.getElementById("showTicket-content").innerText = e.dataset.content;
    document.getElementById("showTicket-reply").innerText = e.dataset.reply===undefined?"":e.dataset.reply;
    document.getElementById("showTicket").style.display = "flex";
    /*==================================添加数据结束=============================================*/
}