//显示各个功能区
function showSection(id) {
    /*====================遍历每一个section，除了传来的id其余的删除active=========================*/
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(id).classList.add('active');
    /*=================================遍历section结束======================================*/

    /*===================遍历每一个功能按钮，除了点击的目标按钮其余的删除active====================*/
    document.querySelectorAll('.nav-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    event.target.classList.add('active');
    /*=================================遍历按钮结束=========================================*/
}
//登出窗口
function logout(){
    document.getElementById('logout').style.display='flex';
}
//将(id)窗口显示出来
function openOverlay(id){
    document.getElementById(id).style.display='flex';
}
//关闭(id)窗口
function closeOverlay(id){
    document.getElementById(id).style.display='none';
}
