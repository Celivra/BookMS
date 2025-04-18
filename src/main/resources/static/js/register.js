document.querySelector("form").addEventListener("submit", function (e) {
    const password1 = document.getElementById("passwd1").value;
    const password2 = document.getElementById("passwd2").value;

    if (password1 !== password2) {
        e.preventDefault(); // 阻止表单提交
        alert("两次输入的密码不一致，请重新输入。");
        return false;
    }
});
function closeOverlay(id){
    document.getElementById(id).style.display='none';
}
