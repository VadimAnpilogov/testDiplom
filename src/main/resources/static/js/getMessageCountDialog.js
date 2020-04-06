function ajaxGet2(){
    $.ajax({
        type : "GET",
        url : window.location + "/countD",
        success: function(result){
            if(result.status === "DoneYes"){
                $('#getResultCountD').empty();

                var menu = document.getElementById("getResultDialog");
                var dialogs = menu.getElementsByTagName("li");
                if(result.data.length !== 0)
                {
                    for (var i = 0; i < result.data.length; i++)
                    {
                        for (var j = 0; j < dialogs.length; j++)
                        {
                            if(result.data[i] === dialogs.item(j).innerText)
                            {
                                if(dialogs.item(j).lastElementChild.id === "svg")
                                    break;
                                dialogs.item(j).insertAdjacentHTML( 'beforeend',
                                    '<svg class="svgMessage" id="svg" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" ' +
                                    'width="64" height="64" ' +
                                    'viewBox="0 0 172 172" ' +
                                    'style=" fill:#000000;"><g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><path d="M0,172v-172h172v172z" fill="none"></path><g fill="#ffffff"><path d="M151.30625,25.53125h-130.6125c-6.9875,0 -12.63125,5.64375 -12.63125,12.63125c0,3.49375 1.47813,6.85312 3.89687,9.1375l61.94688,58.85625c3.35938,3.225 7.65937,4.8375 12.09375,4.8375c4.43438,0 8.6,-1.6125 12.09375,-4.8375l57.78125,-54.95937v76.19063c0,4.56875 -3.7625,8.33125 -8.33125,8.33125h-123.0875c-4.56875,0 -8.33125,-3.7625 -8.33125,-8.33125v-49.45c0,-2.28437 -1.74688,-4.03125 -4.03125,-4.03125c-2.28437,0 -4.03125,1.74688 -4.03125,4.03125v49.45c0,9.00312 7.39063,16.39375 16.39375,16.39375h123.22188c9.00313,0 16.39375,-7.39062 16.39375,-16.39375v-89.7625c0,-0.13437 0,-0.40313 0,-0.5375c-0.80625,-6.45 -6.18125,-11.55625 -12.76562,-11.55625zM154.39688,41.52188l-61.94687,58.85625c-3.62813,3.49375 -9.27188,3.49375 -12.9,0l-61.94687,-58.85625c-0.94062,-0.80625 -1.47812,-2.01562 -1.47812,-3.35937c0,-2.55313 2.01563,-4.56875 4.56875,-4.56875h130.6125c2.55313,0 4.56875,2.01563 4.56875,4.56875c0,1.20938 -0.5375,2.41875 -1.47813,3.35938z"></path></g></g></svg>'
                                );
                            }
                        }
                    }
                }

                console.log("Success: ", result);
            }else{
                console.log("Fail: ", result);
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}
setInterval('ajaxGet2()', 1000);