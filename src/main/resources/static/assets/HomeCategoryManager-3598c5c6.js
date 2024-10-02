import{_ as U,j as $,k as E,A as c,w as H,i as s,C as K,o as y,c as N,a as r,g as t,s as o,I as P,D as R,d as l,e as V,q as d,G as j,H as q,t as C,p as A,h as G,E as F}from"./index-a7376f8a.js";import{u as J,l as O,b as Q,d as W}from"./category-9ad7b7f6.js";const X=g=>(A("data-v-b38b86f4"),g=g(),G(),g),Y={class:"container"},Z=X(()=>r("div",{class:"head"},[r("p",null,[d("分类管理"),r("span",null,"Category Manager")])],-1)),ee={class:"body"},te={class:"menu",style:{}},ae={style:{float:"left","margin-right":"40px","margin-bottom":"20px"}},oe={style:{float:"left"}},le={class:"data"},ne={style:{height:"60px"}},ie={class:"dialog-footer"},se={__name:"HomeCategoryManager",setup(g){J();let e=$({rowData:[],tableData:[],optionItem:{id:0,name:"",description:""},dialogVisible:!1,isLoading:!1,searchText:"",page:{size:10,current:1,count:0}});E(()=>{u()});const v=n=>{n===0?(e.optionItem.id=0,e.optionItem.name="",e.optionItem.description=""):(e.optionItem.id=n.id,e.optionItem.name=n.name,e.optionItem.description=n.description),e.dialogVisible=!0},I=()=>{e.optionItem.name===""?c.warning("名称不能为空！"):Q(e.optionItem).then(n=>{n.code===200?c.success("操作成功！"):c.error(n.msg),e.dialogVisible=!1,u()})},w=n=>{F.confirm("确定要删除吗？","警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{n.refCount>0?c.error("存在关联文章，请先在文章里删除引用。"):W(n.id).then(a=>{a.code===200?c.success("操作成功！"):c.error(a.msg),u()})}).catch(()=>{})},u=()=>{e.isLoading=!0;let n={searchKey:e.searchText};O(n).then(a=>{if(a.code!==200)c.error(a.msg);else{e.rowData=a.data,e.page.count=Math.ceil(e.rowData.length/e.page.size);let _=(e.page.current-1)*e.page.size;e.tableData=e.rowData.slice(_,_+e.page.size)}e.isLoading=!1})};return H(()=>e.page.current,()=>{e.tableData=[],e.isLoading=!0,setTimeout(()=>{let n=(e.page.current-1)*e.page.size;e.tableData=e.rowData.slice(n,n+e.page.size),e.isLoading=!1},200)}),(n,a)=>{const _=s("Plus"),f=s("el-icon"),p=s("el-button"),k=s("Refresh"),D=s("el-button-group"),z=s("Search"),h=s("el-input"),b=s("el-form-item"),x=s("el-form"),m=s("el-table-column"),T=s("el-table"),L=s("el-pagination"),M=s("el-dialog"),S=K("loading");return y(),N("div",Y,[Z,r("div",ee,[r("div",te,[r("div",ae,[t(D,null,{default:o(()=>[t(p,{type:"primary",onClick:a[0]||(a[0]=i=>v(0))},{default:o(()=>[t(f,{class:"el-icon--left"},{default:o(()=>[t(_)]),_:1}),d("添加标签")]),_:1}),t(p,{type:"primary",onClick:u,loading:l(e).isLoading},{default:o(()=>[l(e).isLoading?j("",!0):(y(),V(f,{key:0,class:"el-icon--left"},{default:o(()=>[t(k)]),_:1})),d("刷新列表")]),_:1},8,["loading"])]),_:1})]),r("div",oe,[t(x,{inline:!0,onSubmit:a[2]||(a[2]=P(()=>{},["prevent"]))},{default:o(()=>[t(b,{label:""},{default:o(()=>[t(h,{modelValue:l(e).searchText,"onUpdate:modelValue":a[1]||(a[1]=i=>l(e).searchText=i),onKeyup:q(u,["enter","native"]),placeholder:"按名称或描述搜索"},{append:o(()=>[t(p,{type:"primary",onClick:u},{default:o(()=>[d(" 搜索 "),t(f,{class:"el-icon--right"},{default:o(()=>[t(z)]),_:1})]),_:1})]),_:1},8,["modelValue","onKeyup"])]),_:1})]),_:1})])]),r("div",le,[R((y(),V(T,{data:l(e).tableData,stripe:"",style:{"max-height":"calc(100vh - 212px)"}},{default:o(()=>[t(m,{label:"编号",width:"80"},{default:o(i=>[d(C(i.$index+1+(l(e).page.current-1)*l(e).page.size),1)]),_:1}),t(m,{prop:"name",label:"标签名称",width:"120"}),t(m,{prop:"description",label:"标签描述"}),t(m,{prop:"createTime",label:"创建时间"}),t(m,{prop:"updateTime",label:"更新时间"}),t(m,{fixed:"right",label:"操作",width:"120"},{default:o(i=>[t(p,{link:"",type:"primary",size:"large",onClick:B=>v(i.row)},{default:o(()=>[d("修改")]),_:2},1032,["onClick"]),t(p,{link:"",type:"danger",size:"large",onClick:B=>w(i.row)},{default:o(()=>[d("删除")]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"])),[[S,l(e).isLoading]]),r("div",ne,[t(L,{layout:"prev, pager, next","page-size":l(e).page.size,"page-count":l(e).page.count,"current-page":l(e).page.current,"onUpdate:currentPage":a[3]||(a[3]=i=>l(e).page.current=i),style:{"padding-top":"14px"}},null,8,["page-size","page-count","current-page"])])]),t(M,{modelValue:l(e).dialogVisible,"onUpdate:modelValue":a[7]||(a[7]=i=>l(e).dialogVisible=i),title:l(e).optionItem.id===0?"添加":"修改",width:"500",style:{padding:"0 20px"}},{footer:o(()=>[r("span",ie,[t(p,{size:"large",onClick:a[6]||(a[6]=i=>l(e).dialogVisible=!1)},{default:o(()=>[d("取消")]),_:1}),t(p,{type:"primary",size:"large",onClick:I},{default:o(()=>[d(C(l(e).optionItem.id===0?"添加":"修改"),1)]),_:1})])]),default:o(()=>[t(x,{size:"large","label-position":"right","label-width":"auto",style:{"max-width":"460px"}},{default:o(()=>[t(b,{label:"标签名称："},{default:o(()=>[t(h,{modelValue:l(e).optionItem.name,"onUpdate:modelValue":a[4]||(a[4]=i=>l(e).optionItem.name=i),placeholder:"输入标签名称"},null,8,["modelValue"])]),_:1}),t(b,{label:"标签描述："},{default:o(()=>[t(h,{modelValue:l(e).optionItem.description,"onUpdate:modelValue":a[5]||(a[5]=i=>l(e).optionItem.description=i),type:"textarea",placeholder:"输入标签描述",rows:"5"},null,8,["modelValue"])]),_:1})]),_:1})]),_:1},8,["modelValue","title"])])])}}},pe=U(se,[["__scopeId","data-v-b38b86f4"]]);export{pe as default};
