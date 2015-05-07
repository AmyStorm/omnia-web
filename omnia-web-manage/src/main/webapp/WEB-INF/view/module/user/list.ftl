<#include "${base}/include/header.dec">
<html>
    <body>

        <mainTitle>Title</mainTitle>
        <mainDescription>Description</mainDescription>
        <!-- Custom column filtering -->
        <div class="panel panel-default">
            <#--<div class="panel-heading">-->
                <#--<h3 class="panel-title">Custom column filtering</h3>-->
            <#--</div>-->
            <div class="panel-body">

                <script type="text/javascript">
                    jQuery(document).ready(function($)
                    {
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