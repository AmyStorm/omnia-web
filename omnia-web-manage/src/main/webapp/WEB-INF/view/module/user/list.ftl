<#include "${base}/include/header.dec">
<html>
    <body>
        <div class="page-title">

            <div class="title-env">
                <h1 class="title">DataTable</h1>

                <p class="description">Dynamic table variants with pagination and other controls</p>
            </div>

            <div class="breadcrumb-env">

                <ol id="navigation" class="breadcrumb bc-1">
                    <li>
                        <a href="dashboard-1.html"><i class="fa-home"></i>Home</a>
                    </li>
                    <#list navigation as n>
                        <li>
                            <a href="${n.href}">${n.name}</a>
                        </li>
                    </#list>
                </ol>

            </div>

        </div>

        <!-- Custom column filtering -->
        <div class="panel panel-default">
            <#--<div class="panel-heading">-->
                <#--<h3 class="panel-title">Custom column filtering</h3>-->
            <#--</div>-->
            <div class="panel-body">

                <script type="text/javascript">
                    jQuery(document).ready(function($)
                    {
                        $('#navigation').find('li:last').addClass('active');
                        $("#user_table").dataTable({
                            serverSide: true,
                            ajax: {
                                url: 'user/list',
                                type: 'POST'
                            },
                            "columns": [
//                                { "data": "id" },
                                { "data": "name" },
                                { "data": "lastLoginTime" },
                                { "data": "operate" }
                            ]
//                            "oLanguage" : { // 汉化
//                                "sProcessing" : "正在加载数据...",
//                                "sLengthMenu" : "显示_MENU_条 ",
//                                "sZeroRecords" : "没有您要搜索的内容",
//                                "sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
//                                "sInfoEmpty" : "记录数为0",
//                                "sInfoFiltered" : "(全部记录数 _MAX_  条)",
//                                "sInfoPostFix" : "",
//                                "sSearch" : "搜索",
//                                "sUrl" : "",
//                                "oPaginate" : {
//                                    "sFirst" : "第一页",
//                                    "sPrevious" : " 上一页 ",
//                                    "sNext" : " 下一页 ",
//                                    "sLast" : " 最后一页 "
//                                }
//                            },
//                            "bJQueryUI": true,
//                            "bPaginate" : true,// 分页按钮
//                            "bFilter" : true,// 搜索栏
//                            "bLengthChange" : true,// 每行显示记录数
//                            "iDisplayLength" : 10,// 每页显示行数
//                            "bSort" : false,// 排序
//                            //"aLengthMenu": [[50,100,500,1000,10000], [50,100,500,1000,10000]],//定义每页显示数据数量
//                            //"iScrollLoadGap":400,//用于指定当DataTable设置为滚动时，最多可以一屏显示多少条数据
//                            //"aaSorting": [[4, "desc"]],
//                            "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
//                            "bWidth":true,
//                            //"sScrollY": "62%",
//                            //"sScrollX": "210%",
//                            "bScrollCollapse": true,
//                            "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
//                            "bProcessing" : true,
//                            "bServerSide" : true,
//                            "bDestroy": true,
//                            "bSortCellsTop": true,
//                            "sAjaxSource": '/user/list',
//                            "aoColumns":
//                                    [
////                                        { "mData": "id", 'sClass':'left'},
//                                        { "mData": "name", 'sClass':'center'},
//                                        { "mData": "lastLoginTime", 'sClass':'left'}
//                                        /*
//                                        { "mData": "pass<a href="http://www.it165.net/edu/ebg/" target="_blank" class="keylink">word</a>",'sClass':'left',"mRender":function(data,type,full){
//                                            return "<button type='button' onclick=fuck11("+data+")>"+data+"</button>"
//                                            }
//                                        }
//                                        */
//                                    ],
//                            "fnServerData" : function(sSource, aoData, fnCallback) {
//                                $.ajax({
//                                    "type" : 'post',
//                                    "url" : sSource,
//                                    "dataType" : "json",
//                                    "data" : {
//                                        aoData : JSON.stringify(aoData)
//                                    },
//                                    "success" : function(resp) {
//                                        fnCallback(resp);
//                                    }
//                                });
//
//                            }

                        }).yadcf([
                            {column_number : 0, filter_type: 'text'},
                            {column_number : 1, filter_type: 'range_number'}
//                            {column_number : 2, filter_type: 'text'},
//                            {column_number : 3, filter_type: 'range_number'},
//                            {column_number : 4}
                        ]);
                    });
                </script>

                <table class="table table-striped table-bordered" id="user_table">
                    <thead>
                        <tr class="replace-inputs">
                            <th>name</th>
                            <th>last login time</th>
                            <th>operate</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>Internet Explorer 4.0</td>-->
                        <#--<td>Win 95+</td>-->
                        <#--<td class="center">4</td>-->
                        <#--<td class="center">X</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>Internet Explorer 5.0</td>-->
                        <#--<td>Win 95+</td>-->
                        <#--<td class="center">5</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>Internet Explorer 5.5</td>-->
                        <#--<td>Win 95+</td>-->
                        <#--<td class="center">5.5</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>Internet Explorer 6</td>-->
                        <#--<td>Win 98+</td>-->
                        <#--<td class="center">6</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>Internet Explorer 7</td>-->
                        <#--<td>Win XP SP2+</td>-->
                        <#--<td class="center">7</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Trident</td>-->
                        <#--<td>AOL browser (AOL desktop)</td>-->
                        <#--<td>Win XP</td>-->
                        <#--<td class="center">6</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Firefox 1.0</td>-->
                        <#--<td>Win 98+ / OSX.2+</td>-->
                        <#--<td class="center">1.7</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Firefox 1.5</td>-->
                        <#--<td>Win 98+ / OSX.2+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Firefox 2.0</td>-->
                        <#--<td>Win 98+ / OSX.2+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Firefox 3.0</td>-->
                        <#--<td>Win 2k+ / OSX.3+</td>-->
                        <#--<td class="center">1.9</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Camino 1.0</td>-->
                        <#--<td>OSX.2+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Camino 1.5</td>-->
                        <#--<td>OSX.3+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Netscape 7.2</td>-->
                        <#--<td>Win 95+ / Mac OS 8.6-9.2</td>-->
                        <#--<td class="center">1.7</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Netscape Browser 8</td>-->
                        <#--<td>Win 98SE+</td>-->
                        <#--<td class="center">1.7</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Netscape Navigator 9</td>-->
                        <#--<td>Win 98+ / OSX.2+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.0</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.1</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.1</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.2</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.2</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.3</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.3</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.4</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.4</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.5</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.5</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.6</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">1.6</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.7</td>-->
                        <#--<td>Win 98+ / OSX.1+</td>-->
                        <#--<td class="center">1.7</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Mozilla 1.8</td>-->
                        <#--<td>Win 98+ / OSX.1+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Seamonkey 1.1</td>-->
                        <#--<td>Win 98+ / OSX.2+</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Gecko</td>-->
                        <#--<td>Epiphany 2.20</td>-->
                        <#--<td>Gnome</td>-->
                        <#--<td class="center">1.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>Safari 1.2</td>-->
                        <#--<td>OSX.3</td>-->
                        <#--<td class="center">125.5</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>Safari 1.3</td>-->
                        <#--<td>OSX.3</td>-->
                        <#--<td class="center">312.8</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>Safari 2.0</td>-->
                        <#--<td>OSX.4+</td>-->
                        <#--<td class="center">419.3</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>Safari 3.0</td>-->
                        <#--<td>OSX.4+</td>-->
                        <#--<td class="center">522.1</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>OmniWeb 5.5</td>-->
                        <#--<td>OSX.4+</td>-->
                        <#--<td class="center">420</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>iPod Touch / iPhone</td>-->
                        <#--<td>iPod</td>-->
                        <#--<td class="center">420.1</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Webkit</td>-->
                        <#--<td>S60</td>-->
                        <#--<td>S60</td>-->
                        <#--<td class="center">413</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 7.0</td>-->
                        <#--<td>Win 95+ / OSX.1+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 7.5</td>-->
                        <#--<td>Win 95+ / OSX.2+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 8.0</td>-->
                        <#--<td>Win 95+ / OSX.2+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 8.5</td>-->
                        <#--<td>Win 95+ / OSX.2+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 9.0</td>-->
                        <#--<td>Win 95+ / OSX.3+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 9.2</td>-->
                        <#--<td>Win 88+ / OSX.3+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera 9.5</td>-->
                        <#--<td>Win 88+ / OSX.3+</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Opera for Wii</td>-->
                        <#--<td>Wii</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Nokia N800</td>-->
                        <#--<td>N800</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Presto</td>-->
                        <#--<td>Nintendo DS browser</td>-->
                        <#--<td>Nintendo DS</td>-->
                        <#--<td class="center">8.5</td>-->
                        <#--<td class="center">C/A<sup>1</sup>-->
                        <#--</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>KHTML</td>-->
                        <#--<td>Konqureror 3.1</td>-->
                        <#--<td>KDE 3.1</td>-->
                        <#--<td class="center">3.1</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>KHTML</td>-->
                        <#--<td>Konqureror 3.3</td>-->
                        <#--<td>KDE 3.3</td>-->
                        <#--<td class="center">3.3</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>KHTML</td>-->
                        <#--<td>Konqureror 3.5</td>-->
                        <#--<td>KDE 3.5</td>-->
                        <#--<td class="center">3.5</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Tasman</td>-->
                        <#--<td>Internet Explorer 4.5</td>-->
                        <#--<td>Mac OS 8-9</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">X</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Tasman</td>-->
                        <#--<td>Internet Explorer 5.1</td>-->
                        <#--<td>Mac OS 7.6-9</td>-->
                        <#--<td class="center">1</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Tasman</td>-->
                        <#--<td>Internet Explorer 5.2</td>-->
                        <#--<td>Mac OS 8-X</td>-->
                        <#--<td class="center">1</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>NetFront 3.1</td>-->
                        <#--<td>Embedded devices</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>NetFront 3.4</td>-->
                        <#--<td>Embedded devices</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">A</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>Dillo 0.8</td>-->
                        <#--<td>Embedded devices</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">X</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>Links</td>-->
                        <#--<td>Text only</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">X</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>Lynx</td>-->
                        <#--<td>Text only</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">X</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>IE Mobile</td>-->
                        <#--<td>Windows Mobile 6</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Misc</td>-->
                        <#--<td>PSP browser</td>-->
                        <#--<td>PSP</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">C</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td>Other browsers</td>-->
                        <#--<td>All others</td>-->
                        <#--<td>-</td>-->
                        <#--<td class="center">-</td>-->
                        <#--<td class="center">U</td>-->
                    <#--</tr>-->
                    </tbody>
                    <#--<tfoot>-->
                    <#--<tr>-->
                        <#--<th>Rendering engine</th>-->
                        <#--<th>Browser</th>-->
                        <#--<th>Platform(s)</th>-->
                        <#--<th>Engine version</th>-->
                        <#--<th>CSS grade</th>-->
                    <#--</tr>-->
                    <#--</tfoot>-->
                </table>

            </div>
        </div>



        <!-- Imported styles on this page -->
        <link rel="stylesheet" href="${static}/js/datatables/dataTables.bootstrap.css">

        <script src="${static}/js/datatables/js/jquery.dataTables.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="${static}/js/datatables/dataTables.bootstrap.js"></script>
        <script src="${static}/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
        <script src="${static}/js/datatables/tabletools/dataTables.tableTools.min.js"></script>

    </body>
</html>