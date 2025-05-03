//(打开|关闭)&(编辑信息|修改密码)窗口
function toggleEditUserForm(flag){
    const form = document.getElementById('editForm');
    const passwdform = document.getElementById('editPasswordForm');
    if(flag === 'password'){
        form.style.display = 'none';
        passwdform.style.display = passwdform.style.display === 'none' ? 'block' : 'none';
    }else{
        passwdform.style.display = 'none';
        form.style.display = form.style.display === 'none' ? 'block' : 'none';
    }
}
//显示借阅书籍跟归还书籍的界面
function toggleBookDetail(bookData, BorR) {
    /*==================================显示归还或者借阅窗口===========================================*/
    document.getElementById('borrowForm').style.display = BorR==='borrow'?'block':'none';
    document.getElementById('returnForm').style.display = BorR==='return'?'block':'none';
    document.getElementById('overlay-number').style.display = BorR==='borrow'?'block':'none';
    /*======================================显示操作结束=============================================*/

    /*====================================插入各个属性的值============================================*/
    document.getElementById('overlay-title').innerText = "《"+bookData.title+"》";
    document.getElementById('overlay-ISBN').innerText = "ISBN:"+bookData.isbn;
    document.getElementById('overlay-author').innerText = "作者：" + bookData.author;
    document.getElementById('overlay-type').innerText = "类型：" + bookData.type;
    document.getElementById('overlay-publisher').innerText = "出版社：" + bookData.publisher;
    /*-------------------------------------将日期分开设置--------------------------------------------*/
    const dateStr =  bookData.publisheddate;
    console.log("second:"+dateStr);
    if (dateStr) {
        const [year, month, day] = dateStr.split("-");
        document.getElementById("overlay-publishedDate").innerText =
            `出版日期：${year}年${month}月${day}日`;
    } else {
        document.getElementById("overlay-publishedDate").innerText = "出版日期：暂无";
    }
    /*--------------------------------------插入日期结束--------------------------------------------*/
    document.getElementById('overlay-number').innerText = "所剩数量：" + bookData.number;
    document.getElementById('overlay-desc').innerText = "简介：" + bookData.desc;

    document.getElementById('borrowBookId').value = bookData.bookid;
    document.getElementById('returnbookid').value = bookData.bookid;
    /*======================================插属性值结束============================================*/

    document.getElementById('overlay').style.display = 'flex';

    /*=========================判断如果书籍的数量为0，则关闭借阅书籍的按钮================================*/
    const borrowbutton =  document.getElementById('borrowButton');
    borrowbutton.disabled = (bookData.number == 0);
    /*========================================判断结束==============================================*/
}
//判断输入的两个密码是否相同
document.querySelector("#editPasswordForm form").addEventListener("submit", function (e) {
    const password1 = document.getElementById("password1").value;
    const password2 = document.getElementById("password2").value;

    if (password1 !== password2) {
        e.preventDefault(); // 阻止表单提交
        alert("两次输入的密码不一致，请重新输入。");
        return false;
    }else{
        alert("修改成功");
        return true;
    }
});
//点击书籍卡片时接受点击的书籍的信息
function ClickBookCard(el, BorR){
    const bookid = el.dataset.id
    const isbn = el.dataset.isbn
    const title = el.dataset.title;
    const author = el.dataset.author;
    const type = el.dataset.type;
    const publisher = el.dataset.publisher;
    const publisheddate = el.dataset.publisheddate;
    const number = el.dataset.number;
    const desc = el.dataset.desc;
    console.log("first:"+publisheddate);
    toggleBookDetail({bookid, isbn, title, author, type, publisher, publisheddate, number, desc}, BorR);
}


function viewTicket(e){
    document.getElementById("showTicket-content").innerText = e.dataset.content;
    console.log(e.dataset.content);
    const r = document.getElementById("showTicket-reply");
    if(e.dataset.reply === undefined){
        r.innerText="尚未回复";
    }else {
        r.innerText = e.dataset.reply;
    }

    document.getElementById("showTicket").style.display = "flex";
}