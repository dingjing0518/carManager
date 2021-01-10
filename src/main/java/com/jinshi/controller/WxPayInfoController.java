package com.jinshi.controller;

import com.jinshi.entity.WxPayInfo;
import com.jinshi.service.WxPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/wxPayInfo")
public class WxPayInfoController {

    @Autowired
    private WxPayInfoService wxPayInfoService;

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {

        return wxPayInfoService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/insert")
    public Integer insert(WxPayInfo record) {

        return wxPayInfoService.insert(record);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(WxPayInfo record) {

        return wxPayInfoService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public WxPayInfo selectByPrimaryKey(Integer id) {

        return wxPayInfoService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(WxPayInfo record) {

        return wxPayInfoService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(WxPayInfo record) {

        return wxPayInfoService.updateByPrimaryKey(record);
    }

//    成都仟铭软件科技有限公司
//    智慧警务管理平台V2.0版本
//            著作权申请代码提供

    /******************************
     * 说明：
     * 创建人：
     * 创建日期：
     * 修改人：
     * 修改日期：
     * 修改备注：
     * 版本：2.0.0.0
     ******************************/
//    using System;
//    using System.Collections.Generic;
//    using System.Linq;
//    using System.Text;
//    using DevExpress.XtraGrid;
//    using System.Windows.Forms;
//    using Hoker.Data;
//    using Hoker.Util;
//    using DevExpress.XtraGrid.Columns;
//    using System.Data;
//    using DevExpress.XtraGrid.Views.Grid;
//    using NPOI.SS.UserModel;
//    using System.IO;
//    using Hoker.Model;
//    using DevExpress.Utils.Menu;
//    using System.Collections;
//    using Hoker.Business.Impl;
//
//    namespace Hoker.Control.Model
//    {
//        /// <summary>
//        /// HokerGridControlEx相关扩展
//        /// </summary>
//        public static class GridExtend
//        {
//            /// <summary>
//            /// <para>说明：扩展DXMenuItemCollection批量添加菜单</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <typeparam name="T"></typeparam>
//            /// <param name="items">The items.</param>
//            /// <param name="collection">The collection.</param>
//            public static void AddRange<T>(this DXMenuItemCollection items, IEnumerable<T> collection) where T : DXMenuItem
//            {
//                foreach (T item in collection)
//                {
//                    items.Add(item);
//                }
//            }
//            /// <summary>
//            /// <para>说明：GridControl数据行</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-22 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static int DataRowCount(this HokerGridControlEx control)
//            {
//                try
//                {
//                    return (control.GridControl.DataSource as DataTable).Rows.Count;
//                }
//                catch (Exception)
//                {
//
//                }
//                return 0;
//            }
//            /// <summary>
//            /// <para>说明：获取所有RowHandler</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-03-21 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <returns>System.Int32[].</returns>
//            public static int[] RowHandlers(this GridView GridView)
//            {
//                if (GridView == null) return new int[] { };
//
//                DataTable table = GridView.GridControl.DataSourceTable();
//
//                int[] rows = new int[table.Rows.Count];
//                for (int i = 0; i < table.Rows.Count; i++)
//                {
//                    rows[i] = GridView.GetRowHandle(i);
//                }
//
//                return rows;
//            }
//            /// <summary>
//            /// <para>说明：检测是否某列是否正处于排序状态</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-06-06 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            public static bool HasSortColumn(this GridView GridView, string columnName = "")
//            {
//                if (string.IsNullOrEmpty(columnName))
//                {
//                    var columns = GridView.Columns.Where(x => x.SortOrder != DevExpress.Data.ColumnSortOrder.None);
//                    return columns == null || columns.Count() != 0;
//                }
//                else
//                {
//                    var columns = GridView.Columns.Where(x => x.SortOrder != DevExpress.Data.ColumnSortOrder.None && x.FieldName == columnName);
//                    return columns == null || columns.Count() != 0;
//                }
//            }
//            /// <summary>
//            /// <para>说明：表格必须录入验证</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-01-04 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="nullFields">不能为空的字段.</param>
//            /// <returns><c>true</c> if XXXX, <c>false</c> otherwise.</returns>
//            public static bool ValidateNullRequired(this GridView GridView, DataRow[] nullFields)
//            {
//                DataTable dtBillItem = GridView.GridControl.DataSource as DataTable;
//                if (dtBillItem == null) return false;
//                DataColumnCollection dtBillRow = dtBillItem.Columns;
//                GridColumnCollection gridColumns = GridView.Columns;
//
//                if (dtBillItem != null && dtBillItem.Rows.Count > 0)
//                {
//                    DataRow[] modifiedRows = dtBillItem.Rows
//                            .Cast<DataRow>()
//                            .Where(x => x.RowState == DataRowState.Modified || x.RowState == DataRowState.Added)
//                                            .ToArray();
//
//                    foreach (DataRow drGrid in modifiedRows)
//                    {
//                        foreach (DataRow dr in nullFields)
//                        {
//                            string fieldName = dr["FieldName"] + "";
//                            string fieldText = dr["FieldCaption"] + "";
//                            if (string.IsNullOrEmpty(drGrid[fieldName] + ""))
//                            {
//                                MessageUtil.Show(string.Format(MessageKeys.NullField, fieldText));
//
//                                GridView.Focus();
//                                GridView.FocusedRowHandle = dtBillItem.Rows.IndexOf(drGrid);
//                                GridView.FocusedColumn = gridColumns[fieldName];
//                                GridView.SelectCell(GridView.FocusedRowHandle, gridColumns[fieldName]);
//                                GridView.ShowEditorByMouse();
//                                return false;
//                            }
//                        }
//                    }
//                }
//                return true;
//            }
//            /// <summary>
//            /// <para>说明：保存个性化列</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="key">The key.</param>
//            /// <returns><c>true</c> if XXXX, <c>false</c> otherwise.</returns>
//            public static bool SaveCustomColumnToDatabase(this GridView GridView, string key)
//            {
//                if (string.IsNullOrEmpty(key)) return false;
//
//                StringBuilder sBuilder = new StringBuilder();
//                foreach (GridColumn col in GridView.Columns)
//                {
//                    ControlModel model = col.Tag as ControlModel;
//                    if (model != null)
//                    {
//                        string sqlValue = string.Format(@"INSERT INTO {0}(FromKey,UnionKey,OrderID,FieldName,FieldCaption,Width,Disabled,UserID,UserName,CreateDate,IsFixColumn)
//                        VALUES('{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}',GETDATE(),'{10}')",
//                        MessageKeys.SettingTableName, model.FromKey, key, col.VisibleIndex, col.FieldName, col.Caption, col.Width, !col.Visible,
//                                HokerInfo.Instance.UserId, HokerInfo.Instance.UserName, col.Fixed == FixedStyle.Left);
//
//                        sBuilder.Append(sqlValue);
//                    }
//                }
//
//                return BaseImpl.ExecSqlValue(sBuilder.ToString()) > 0;
//            }
//            /// <summary>
//            /// <para>说明：更新个性列</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="key">The key.</param>
//            /// <returns><c>true</c> if XXXX, <c>false</c> otherwise.</returns>
//            public static bool UpdateCustomColumnToDatabase(this GridView GridView, string key)
//            {
//                if (string.IsNullOrEmpty(key)) return false;
//
//                StringBuilder sBuilder = new StringBuilder();
//                foreach (GridColumn col in GridView.Columns)
//                {
//                    string sqlValue = string.Format(@"UPDATE {0} SET OrderID='{1}',Width='{2}',Disabled='{3}',IsFixColumn='{4}' WHERE UnionKey='{5}' AND FieldName='{6}' AND UserID='{7}'",
//                        MessageKeys.SettingTableName, col.VisibleIndex, col.Width, !col.Visible, col.Fixed == FixedStyle.Left, key, col.FieldName, HokerInfo.Instance.UserId);
//                    sBuilder.Append(sqlValue);
//                }
//
//                return BaseImpl.ExecSqlValue(sBuilder.ToString()) > 0;
//            }
//            /// <summary>
//            /// <para>说明：更新系统配置列</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="key">The key.</param>
//            /// <returns><c>true</c> if XXXX, <c>false</c> otherwise.</returns>
//            public static bool UpdateSystemCustomColumnToDatabase(this GridView GridView, string key)
//            {
//                if (string.IsNullOrEmpty(key)) return false;
//
//                StringBuilder sBuilder = new StringBuilder();
//                foreach (GridColumn col in GridView.Columns)
//                {
//                    string sqlValue = string.Format(@"UPDATE {0} SET OrderID='{1}',Width='{2}',Disabled='{3}' WHERE UnionKey='{4}' AND FieldName='{5}'",
//                        MessageKeys.SettingSystemTableName, col.VisibleIndex == -1 ? 999 : (col.VisibleIndex + 1), col.Width, !col.Visible, key, col.FieldName);
//                    sBuilder.Append(sqlValue);
//                }
//
//                return BaseImpl.ExecSqlValue(sBuilder.ToString()) > 0;
//            }
//            /// <summary>
//            /// <para>说明：重置个性列</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="key">The key.</param>
//            /// <param name="table">新列数据</param>
//            /// <returns><c>true</c> if XXXX, <c>false</c> otherwise.</returns>
//            public static bool ResetCustomColumnToDatabase(this GridView GridView, string key)
//            {
//                if (string.IsNullOrEmpty(key)) return false;
//
//                try
//                {
//                    string sqlValue = string.Format("DELETE {0} WHERE UnionKey='{1}' AND UserID='{2}'", MessageKeys.SettingTableName, key, HokerInfo.Instance.UserId);
//                    BaseImpl.ExecSqlValue(sqlValue);
//                    return true;
//                }
//                catch (Exception)
//                {
//                    return false;
//                }
//            }
//            /// <summary>
//            /// <para>说明：获取筛选的DataTable</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            public static DataTable GetFilteredAndSortedDataToDataTable(this GridView view)
//            {
//                DataTable _dt = view.GridControl.DataSource as DataTable;
//                if (_dt == null)
//                    return null;
//                DataTable dt = _dt.Clone();
//                for (int i = 0; i < view.RowCount; i++)
//                {
//                    if (view.IsGroupRow(i)) continue;
//                    var dr = view.GetDataRow(i);
//                    if (dr == null) continue;
//                    dt.Rows.Add(dr.ItemArray);
//                }
//                return dt;
//            }
//            /// <summary>
//            /// <para>说明：通过数据库获取个性配置列</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="key">The key.</param>
//            /// <returns>DataTable.</returns>
//            public static DataTable GetCustomColumnByDatabase(this GridView GridView, string key)
//            {
//                try
//                {
//                    // 读取普通用户读取个人配置，没有则读取系统默认.
//                    string sqlValue = string.Format("SELECT * FROM {0} WHERE UnionKey='{1}' AND UserId='{2}'", MessageKeys.SettingTableName, key, HokerInfo.Instance.UserId);
//                    return BaseImpl.GetDataTableResult(sqlValue);
//                }
//                catch (Exception)
//                {
//                }
//                return null;
//            }
//            /// <summary>
//            /// <para>说明：清除列排序</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-30 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="column">The column.</param>
//            /// <returns>GridColumn.</returns>
//            public static GridColumn ClearSort(this GridColumn column)
//            {
//                column.SortOrder = DevExpress.Data.ColumnSortOrder.None;
//                return column;
//            }
//            /// <summary>
//            /// <para>说明：清除所有列排序</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-30 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="columns">The columns.</param>
//            /// <returns>GridColumnCollection.</returns>
//            public static GridColumnCollection ClearAllSort(this GridColumnCollection columns)
//            {
//                foreach (GridColumn item in columns)
//                {
//                    item.ClearSort();
//                }
//                return columns;
//            }
//            /// <summary>
//            /// <para>说明：提交当前行数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-01-02 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            public static void PostFocusRow(this GridView GridView)
//            {
//                GridView.CloseEditor();
//                GridView.PostEditor();
//                GridView.UpdateCurrentRow();
//            }
//            /// <summary>
//            /// <para>说明：设置GridView选中行</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-12-05 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="handler">The handle.</param>
//            public static void SelectRowHandler(this GridView GridView, int handle, bool isClearSelection = false)
//            {
//                if (handle < 0) handle = 0;
//                if (isClearSelection) GridView.ClearSelection();
//                GridView.FocusedRowHandle = handle;
//                GridView.SelectRow(handle);
//            }
//            /// <summary>
//            /// <para>说明：设置表格右键菜单</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-12-19 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="GridView">The grid view.</param>
//            /// <param name="table">The table.</param>
//            /// <param name="handler">The handler.</param>
//            public static void SetGridRightMenus(this HokerGridControlEx GridControl, DataTable table, HokerDynamicModel model, EventHandler handler = null, HokerControl control = null, ContextMenuStrip menu = null)
//            {
//                BaseRightMenu rightMenu = new GridViewMenuStrip();
//                rightMenu.SetRightMenus(GridControl, table, model, control, menu);
//                rightMenu.SetRightCallback(handler);
//            }
//            /// <summary>
//            /// <para>说明：设置图标右键菜单</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-01-29 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="chartControl">The chart control.</param>
//            /// <param name="table">The table.</param>
//            /// <param name="model">The model.</param>
//            /// <param name="handler">The handler.</param>
//            public static void SetChartRightMenus(this HokerChartControlEx chartControl, DataTable table, HokerDynamicModel model, EventHandler handler = null)
//            {
//                BaseRightMenu rightMenu = new ChartViewMenuStrip();
//                rightMenu.SetRightMenus(chartControl, table, model, null, null);
//                rightMenu.SetRightCallback(handler);
//            }
//            /// <summary>
//            /// <para>说明：表格导出文件</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static bool ToExcel(this GridControl control, DataTable table = null)
//            {
//                if (control == null) return false;
//
//                SaveFileDialog dialog = new SaveFileDialog();
//                dialog.Title = "导出";
//                dialog.Filter = "Excel文件(*.xls)|*.xls|Excel2007|*.xlsx";
//
//                DialogResult result = dialog.ShowDialog();
//                if (result == DialogResult.OK)
//                {
//                    WaitForm.ShowForm("导出中,请稍后...");
//
//                    try
//                    {
//                        FrmExport export = new FrmExport();
//                        GridControl newControl = export.ReplaceBitColumn(control, table);
//
//                        if (dialog.FilterIndex == 1)
//                        {
//                            newControl.ExportToXls(dialog.FileName);
//                        }
//                        if (dialog.FilterIndex == 2)
//                        {
//                            newControl.ExportToXlsx(dialog.FileName);
//                        }
//                        MessageUtil.Show(MessageKeys.ExportSuccess);
//                        return true;
//                    }
//                    catch (Exception ex)
//                    {
//                        LogUtil.Instance.WriteError(ex);
//                        MessageUtil.Show(MessageKeys.ExportFault);
//                    }
//                    finally
//                    {
//                        WaitForm.HideForm();
//                    }
//                }
//                return false;
//            }
//            /// <summary>
//            /// <para>说明：导出Pdf</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-08-02 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static bool ToPdf(this GridControl control, DataTable table = null)
//            {
//                if (control == null) return false;
//
//                SaveFileDialog dialog = new SaveFileDialog();
//                dialog.Title = "导出";
//                dialog.Filter = "PDF文件|*.PDF";
//
//                DialogResult result = dialog.ShowDialog();
//                if (result == DialogResult.OK)
//                {
//                    WaitForm.ShowForm("导出中,请稍后...");
//
//                    try
//                    {
//                        FrmExport export = new FrmExport();
//                        GridControl newControl = export.ReplaceBitColumn(control, table);
//
//                        newControl.ExportToPdf(dialog.FileName);
//                        MessageUtil.Show(MessageKeys.ExportSuccess);
//                        return true;
//                    }
//                    catch (Exception ex)
//                    {
//                        LogUtil.Instance.WriteError(ex);
//                        MessageUtil.Show(MessageKeys.ExportFault);
//                    }
//                    finally
//                    {
//                        WaitForm.HideForm();
//                    }
//                }
//                return false;
//            }
//            /// <summary>
//            /// <para>说明：导出Rtf</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-08-02 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static bool ToRtf(this GridControl control, DataTable table = null)
//            {
//                if (control == null) return false;
//
//                SaveFileDialog dialog = new SaveFileDialog();
//                dialog.Title = "导出";
//                dialog.Filter = "RTF文件|*.RTF";
//
//                DialogResult result = dialog.ShowDialog();
//                if (result == DialogResult.OK)
//                {
//                    WaitForm.ShowForm("导出中,请稍后...");
//
//                    try
//                    {
//                        FrmExport export = new FrmExport();
//                        GridControl newControl = export.ReplaceBitColumn(control, table);
//
//                        newControl.ExportToRtf(dialog.FileName);
//                        MessageUtil.Show(MessageKeys.ExportSuccess);
//                        return true;
//                    }
//                    catch (Exception ex)
//                    {
//                        LogUtil.Instance.WriteError(ex);
//                        MessageUtil.Show(MessageKeys.ExportFault);
//                    }
//                    finally
//                    {
//                        WaitForm.HideForm();
//                    }
//                }
//                return false;
//            }
//            /// <summary>
//            /// <para>说明：导出Html</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-08-02 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static bool ToHtml(this GridControl control, DataTable table = null)
//            {
//                if (control == null) return false;
//
//                SaveFileDialog dialog = new SaveFileDialog();
//                dialog.Title = "导出";
//                dialog.Filter = "HTML文件|*.html";
//
//                DialogResult result = dialog.ShowDialog();
//                if (result == DialogResult.OK)
//                {
//                    WaitForm.ShowForm("导出中,请稍后...");
//
//                    try
//                    {
//                        FrmExport export = new FrmExport();
//                        GridControl newControl = export.ReplaceBitColumn(control, table);
//
//                        newControl.ExportToHtml(dialog.FileName);
//                        MessageUtil.Show(MessageKeys.ExportSuccess);
//                        return true;
//                    }
//                    catch (Exception ex)
//                    {
//                        LogUtil.Instance.WriteError(ex);
//                        MessageUtil.Show(MessageKeys.ExportFault);
//                    }
//                    finally
//                    {
//                        WaitForm.HideForm();
//                    }
//                }
//                return false;
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-15 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            public static bool ToExcelTemplate(this GridControl control)
//            {
//                if (control == null) return false;
//
//                SaveFileDialog dialog = new SaveFileDialog();
//                dialog.Title = "导出";
//                dialog.Filter = "Excel文件(*.xls)|*.xls|Excel2007|*.xlsx|PDF文件|*.PDF|RTF文件|*.RTF|HTML文件|*.html";
//
//                DialogResult result = dialog.ShowDialog();
//                if (result == DialogResult.OK)
//                {
//                    WaitForm.ShowForm("导出中,请稍后...");
//                    try
//                    {
//                        if (dialog.FilterIndex == 1)
//                        {
//                            control.ExportToXls(dialog.FileName);
//                        }
//                        if (dialog.FilterIndex == 2)
//                        {
//                            control.ExportToXlsx(dialog.FileName);
//                        }
//                        if (dialog.FilterIndex == 3)
//                        {
//                            control.ExportToPdf(dialog.FileName);
//                        }
//                        if (dialog.FilterIndex == 4)
//                        {
//                            control.ExportToRtf(dialog.FileName);
//                        }
//                        if (dialog.FilterIndex == 5)
//                        {
//                            control.ExportToHtml(dialog.FileName);
//                        }
//
//                        MessageUtil.Show(MessageKeys.ExportSuccess);
//                        return true;
//                    }
//                    catch (Exception ex)
//                    {
//                        LogUtil.Instance.WriteError(ex);
//                        MessageUtil.Show(MessageKeys.ExportFault);
//                    }
//                    finally
//                    {
//                        WaitForm.HideForm();
//                    }
//                }
//                return false;
//            }
//            /// <summary>
//            /// <para>说明：获取GridControl的DataTable</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-12-05 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="control">The control.</param>
//            /// <returns>DataTable.</returns>
//            public static DataTable DataSourceTable(this GridControl control)
//            {
//                DataTable table = control.DataSource == null ? new DataTable() : control.DataSource as DataTable;
//                return table.TrimDeleteRows().TrimEmptyRows();
//            }
//            /// <summary>
//            /// <para>说明：导入到表格中</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-15 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="colFields">The col fields.</param>
//            public static DataTable ToExcelDataTable(this GridView GridView, string fileName, string colFields)
//            {
//                DataTable table = new DataTable();
//
//                try
//                {
//                    ISheet sheet = ExcelUtil.GetSheet(fileName);
//                    IRow headerRow = sheet.GetRow(0);
//
//                    // 添加列
//                    for (int i = 0; i < headerRow.LastCellNum; i++)
//                    {
//                        ICell cell = headerRow.GetCell(i);
//                        GridColumn col = GridView.VisibleColumns.OfType<GridColumn>().FirstOrDefault(x => x.Caption == cell + "");
//                        if (col != null)
//                        {
//                            table.Columns.Add(col.FieldName);
//                        }
//                        else
//                        {
//                            table.Columns.Add(cell + "");
//                        }
//                    }
//                    // 添加行
//                    for (int i = sheet.FirstRowNum + 1; i <= sheet.LastRowNum; i++)
//                    {
//                        IRow row = sheet.GetRow(i);
//                        DataRow dataRow = table.NewRow();
//
//                        for (int j = row.FirstCellNum; j < headerRow.LastCellNum; j++)
//                        {
//                            ICell cell = row.GetCell(j);
//                            if (cell != null)
//                            {
//                                if (cell.CellType == CellType.Numeric && NPOI.SS.UserModel.DateUtil.IsCellDateFormatted(cell))
//                                {
//                                    // 日期格式
//                                    dataRow[j] = cell.DateCellValue.ToString("yyyy-MM-dd");
//                                }
//                                else if (cell.CellType == CellType.Numeric || cell.CellType == CellType.Formula)
//                                {
//                                    // 浮点类型、存在计算公式
//                                    dataRow[j] = cell.NumericCellValue;
//                                }
//                                else
//                                {
//                                    // 字符串形式
//                                    dataRow[j] = cell + "";
//                                }
//                            }
//                        }
//                        table.Rows.Add(dataRow);
//                    }
//                }
//                catch (InvalidOperationException)
//                {
//                    MessageUtil.Show(MessageKeys.DeleteLastTotalRow);
//                }
//                catch (IOException)
//                {
//                    MessageUtil.Show(string.Format(MessageKeys.UseingFile, Path.GetFileName(fileName)));
//                }
//                catch (Exception ex)
//                {
//                    MessageUtil.Show(MessageKeys.ImportFault + ex.Message);
//                }
//                return table.TrimEmptyRows();
//            }
//            /// <summary>
//            /// <para>说明：获取Grid列，按xxx字符分割</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-15 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="columns">The columns.</param>
//            public static string ToString(this GridColumnCollection columns, char spiltChar)
//            {
//                string fields = string.Empty;
//
//                foreach (GridColumn col in columns)
//                {
//                    fields += col.FieldName + spiltChar;
//                }
//                return fields.TrimEnd(spiltChar);
//            }
//        }
//    }
//
//
//    using System;
//    using System.Collections.Generic;
//    using System.ComponentModel;
//    using System.Drawing;
//    using System.Data;
//    using System.Linq;
//    using System.Text;
//    using System.Windows.Forms;
//    using Hoker.Model;
//    using Hoker.Control.Model;
//    using Hoker.Util;
//    using Hoker.Business.Impl;
//    using DevExpress.XtraGrid.Views.Grid;
//    using Hoker.Data;
//    using DevExpress.XtraEditors;
//
//    namespace Hoker.Control.Data
//    {
//        public partial class HokerModuleEx : UserControl
//        {
//        public HokerModuleEx()
//            {
//                InitializeComponent();
//            }
//
//            /// <summary>
//            /// 左侧表格查询条件
//            /// </summary>
//            private HokerControl mLeftGridSearchObj;
//            /// <summary>
//            /// 左侧条件
//            /// </summary>
//            public string LeftCond;
//            /// <summary>
//            /// 系统模块实体对象
//            /// </summary>
//            /// <value>The system model.</value>
//            public ModuleModel ModuleModelObj;
//            /// <summary>
//            /// 左侧数据模块对象
//            /// </summary>
//            public ControlModel LeftControlModelobj;
//            /// <summary>
//            /// 调用动态链接库默认传递参数对象
//            /// </summary>
//            public HokerDynamicModel Model { get; private set; }
//            /// <summary>
//            /// 底部表格数据
//            /// </summary>
//            public List<GridDetailModel> Details;
//            /// <summary>
//            /// 查询条件
//            /// </summary>
//            /// <value>The search object.</value>
//            public HokerControl SearchObj { get { return this.HgmdMainGridView.SearchObj; } }
//            /// <summary>
//            /// 带操作表格的自定义控件对象
//            /// </summary>
//            public HokerGridModuleEx ModuleGridObj { get { return this.HgmdMainGridView.ModuleGridObj; } }
//            /// <summary>
//            /// Gets the module grid detail object.
//            /// </summary>
//            /// <value>The module grid detail object.</value>
//            public HokerGridModuleDetailEx ModuleGridDetailObj { get { return this.HgmdMainGridView; } }
//            /// <summary>
//            /// 左侧树结构对象
//            /// </summary>
//            /// <value>The TreeView object.</value>
//            public HokerTreeViewEx LeftTreeViewObj { get { return this.HtvLeftTreeView; } }
//            /// <summary>
//            /// 左侧表格对象
//            /// </summary>
//            /// <value>The left grid view object.</value>
//            public HokerGridControlEx LeftGridViewObj { get { return this.HgcLeftGridView; } }
//
//            /// <summary>
//            /// 左侧表格行点击事件回调
//            /// </summary>
//            public event EventHandler OnLeftGridViewRowClickCallBack;
//            /// <summary>
//            /// 左侧树结构选中节后点回调
//            /// </summary>
//            public event EventHandler OnLeftTreeViewRowClickCallBack;
//
//            /// <summary>
//            /// <para>说明：创建界面</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-09-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="menuCode">The menu code.</param>
//            public void InitControls(HokerDynamicModel model)
//            {
//                if (model != null)
//                {
//                    this.Model = model;
//                    this.ModuleModelObj = new ModuleModel(ModuleImpl.Instance.GetBaseModule(this.Model.ModuleCode));
//                    this.Model.GridEnumObj = GetGridType();
//
//                    switch (this.ModuleModelObj.MenuType)
//                    {
//                        case 1: // 左侧为树节点
//                            this.HgcLeftGridView.Visible = false;
//                            this.HtvLeftTreeView.Visible = true;
//                            this.ModuleGridObj.ParentTreeViewObj = this.HtvLeftTreeView;
//                            this.InitlizeSpiltLocation();
//                            this.SetLeftTreeView();
//                            if (Model.HasOperPrivilege() && model.IsBaseModule)
//                            {
//                                // 有权限则允许拖拽
//                                GridDragTree dragTree = new GridDragTree(this.ModuleGridObj.GridControlObj.GridViewObj, this.HtvLeftTreeView.TreeView);
//                                dragTree.OnDragComplete += new GridDragTreeCompleteEventHandler(OnDragTreeCompleted);
//                            }
//                            break;
//                        case 2: // 左侧为表格
//                            this.HgcLeftGridView.Visible = true;
//                            this.HtvLeftTreeView.Visible = false;
//                            this.InitlizeSpiltLocation();
//                            this.SetLeftGridView();
//                            if (Model.HasOperPrivilege() && model.IsBaseModule)
//                            {
//                                // 有权限则允许拖拽
//                                GridDragGrid dragGrid = new GridDragGrid(this.ModuleGridObj.GridControlObj.GridViewObj, this.HgcLeftGridView.GridViewObj);
//                                dragGrid.OnDragComplete += new GridFragGridCompleteEventHandler(OnDragGridCompleted);
//                            }
//                            break;
//                        case 3: // 左侧为空
//                        default:
//                            this.scc_container.PanelVisibility = SplitPanelVisibility.Panel2;
//                            break;
//                    }
//                    this.ModuleGridDetailObj.InitializeControl(this.ModuleModelObj, this.Model);
//                }
//                else
//                {
//                    MessageUtil.Show(MessageKeys.MenuCodeIsNotNull);
//                }
//            }
//
//            /// <summary>
//            /// <para>说明：设置分割条位置</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            void InitlizeSpiltLocation()
//            {
//                try
//                {
//                    string width = IniUtil.ReadMenuConfig(string.Format("BaseWidth_{0}", this.Model.ModuleCode));
//                    if (!string.IsNullOrEmpty(width))
//                    {
//                        this.scc_container.SplitterPosition = Convert.ToInt32(width);
//                    }
//                }
//                catch (Exception ex)
//                {
//                    LogUtil.Instance.WriteError(ex);
//                }
//            }
//            /// <summary>
//            /// <para>说明：获取配置模块表格展现方式</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-08-09 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="model">The model.</param>
//            /// <returns>GridEnum.</returns>
//            GridEnum GetGridType()
//            {
//                if (this.ModuleModelObj == null) return GridEnum.GridView;
//
//                int formType = ModuleImpl.Instance.GetBaseType(this.ModuleModelObj.FromKey);
//
//                GridEnum gridEnum = GridEnum.GridView;
//                if (formType == 1) gridEnum = GridEnum.BandedGridView;
//                if (formType == 2) gridEnum = GridEnum.GridView;
//
//                return gridEnum;
//            }
//            /// <summary>
//            /// <para>说明：加载主表格数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-01-31 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="where">The where key.</param>
//            void SetMainGridView(string where = "")
//            {
//                string sqlValue = ReplaceUtil.ReplaceWhereCond(this.ModuleModelObj.TableSQL) + where;
//
//                // 左侧gridView时,需要替换sql参数(包含查询条件、表格选中行).
//                //if (this.HgcLeft.Visible)
//                //{
//                //    sqlValue = this._leftGridSearchObj != null ? this._leftGridSearchObj.ReplaceParentControlValue(sqlValue) : sqlValue;
//                //    sqlValue = ReplaceUtil.ReplaceRowParam(this.HgcLeft.GridView.GetFocusedDataRow(), sqlValue);
//                //}
//
//                this.ModuleGridObj.SearchObj.SearchGrid(sqlValue, false);
//            }
//            /// <summary>
//            /// <para>说明：设置左侧树数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-10-25 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            void SetLeftTreeView()
//            {
//                this.LeftControlModelobj = new ControlModel(ModuleImpl.Instance.GetBaseLeftField(this.ModuleModelObj.FromKey));
//                if (this.LeftControlModelobj != null && this.LeftControlModelobj.ID > 0)
//                {
//                    this.ModuleGridObj.ParentUnionField = this.LeftControlModelobj.FieldName;
//                    this.HtvLeftTreeView.TreeNodeKeyField = this.ModuleGridObj.ParentKeyField = this.LeftControlModelobj.ValueMember;
//                    this.HtvLeftTreeView.TreeNodeTextField = this.LeftControlModelobj.DisplayMember;
//                    this.HtvLeftTreeView.TreeNodeAfterSelect += new TreeViewEventHandler(OnTreeNodeClick);
//                    this.HtvLeftTreeView.BindTreeView(BaseImpl.GetDataTableResult(this.LeftControlModelobj.FieldSQL));
//                }
//                else
//                {
//                    this.scc_container.PanelVisibility = SplitPanelVisibility.Panel2;
//                }
//            }
//            /// <summary>
//            /// <para>说明：设置左侧表格查询条件</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-16 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            void SetLeftGridCondtion()
//            {
//                DataTable table = BaseImpl.GetCustomQueryFields(this.LeftControlModelobj.FromKey + "_Cond");
//                string searchSql = this.LeftControlModelobj.FieldSQL + LeftCond;
//                if (table != null && table.Rows.Count > 0)
//                {
//                    // 自定义查询条件
//                    this.labelControl1.Visible = false;
//                    this.mLeftGridSearchObj = new HokerControl(searchSql, this.HgcLeftGridView);
//                    this.pl_left_top.Height = this.mLeftGridSearchObj.CreateSearchControl(table, this.pl_left_top);
//                    this.mLeftGridSearchObj.SearchGrid();
//                }
//                else
//                {
//                    this.HgcLeftGridView.GridControl.DataSource = BaseImpl.GetDataTableResult(searchSql);
//                }
//            }
//            /// <summary>
//            /// <para>说明：设置左侧表格数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-10-25 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            void SetLeftGridView()
//            {
//                this.LeftControlModelobj = new ControlModel(ModuleImpl.Instance.GetBaseLeftField(this.ModuleModelObj.FromKey));
//                if (this.LeftControlModelobj != null)
//                {
//                    this.ModuleGridObj.ParentKeyField = this.LeftControlModelobj.ValueMember;
//                    this.ModuleGridObj.ParentUnionField = this.LeftControlModelobj.FieldName;
//                    this.ModuleGridObj.ParentGridObj = this.HgcLeftGridView; // 方便右侧添加数据自动带出相同字段值
//
//                    DataTable dtTable = BaseImpl.GetAllGridColumns(this.LeftControlModelobj.FromKey);
//                    DataTable dtColorTable = BaseImpl.GetGridColors(this.LeftControlModelobj.FromKey);
//
//                    this.HgcLeftGridView.GridViewObj.RowClick += new DevExpress.XtraGrid.Views.Grid.RowClickEventHandler(OnGridViewRowClick);
//                    this.HgcLeftGridView.SetGridRightMenus(BaseImpl.GetGridRightMenus(this.LeftControlModelobj.FromKey), this.Model, OnGridViewRightCallBack, this.mLeftGridSearchObj);
//                    this.HgcLeftGridView.SetGridRowColors(dtColorTable);
//                    this.HgcLeftGridView.SetReadOnlyColumns(dtTable, this.LeftControlModelobj.FromKey);
//                    this.SetLeftGridCondtion();
//                }
//            }
//            /// <summary>
//            /// <para>说明：拼接右边数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-05-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sqlValue">The SQL value.</param>
//            /// <returns>System.String.</returns>
//            string ReplaceLeftOrGridValue(string sqlValue)
//            {
//                if (string.IsNullOrWhiteSpace(sqlValue)) return sqlValue;
//                // 替换左侧树结构
//                if (this.HtvLeftTreeView != null && this.HtvLeftTreeView.Visible)
//                {
//                    TreeNode tn = this.HtvLeftTreeView.TreeView.SelectedNode;
//                    if (tn == null)
//                        tn = this.HtvLeftTreeView.TreeView.Nodes[0];
//
//                    string ids = this.HtvLeftTreeView.GetCheckValues();
//                    if (!sqlValue.ToUpper().Contains("EXEC"))
//                    {
//
//                        sqlValue = ReplaceUtil.ReplaceWhereCond(sqlValue);
//                        if (!string.IsNullOrWhiteSpace(ids))
//                        {
//                            sqlValue += " and " + this.ModuleGridObj.ParentUnionField + " in (" + ids.Trim(',') + ")";
//                        }
//                        else
//                        {
//                            sqlValue += this.HtvLeftTreeView.TreeView.Focused ? " and " + this.ModuleGridObj.ParentUnionField + " like '" + tn.Name + "%'" : "";
//                        }
//                    }
//                    sqlValue = ReplaceUtil.ReplaceTreeViewParentKeyCond(sqlValue, ids);
//                }
//                // 替换左侧表格
//                if (this.HgcLeftGridView != null && this.HgcLeftGridView.Visible)
//                {
//                    GridView gridview = this.HgcLeftGridView.GridControl.DefaultView as GridView;
//                    DataRow rowItem = gridview.GetFocusedDataRow();
//                    if (rowItem != null)
//                    {
//                        if (!sqlValue.ToLower().StartsWith("exec"))
//                        {
//                            sqlValue = ReplaceUtil.ReplaceWhereCond(sqlValue);
//                            sqlValue += " and " + this.ModuleGridObj.ParentUnionField + "='" + rowItem[this.ModuleGridObj.ParentKeyField] + "'";
//                        }
//                    }
//                }
//                return sqlValue;
//            }
//
//            /// <summary>
//            /// <para>说明：刷新右侧表格数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-03-19 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="DevExpress.Data.SelectionChangedEventArgs"/> instance containing the event data.</param>
//            void OnGridViewRowClick(object sender, DevExpress.XtraGrid.Views.Grid.RowClickEventArgs e)
//            {
//                this.SetMainGridView();
//
//                if (this.OnLeftGridViewRowClickCallBack != null)
//                    this.OnLeftGridViewRowClickCallBack(this.HgcLeftGridView, null);
//            }
//            /// <summary>
//            /// <para>说明：右键菜单执行后刷新数据，刷新规则按照上一次查询条件刷新</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-03-19 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnGridViewRightCallBack(object sender, EventArgs e)
//            {
//                if (this.mLeftGridSearchObj != null)
//                {
//                    this.mLeftGridSearchObj.SearchLastGrid();
//                }
//                else
//                {
//                    string searchSql = this.LeftControlModelobj.FieldSQL + " " + LeftCond;
//                    this.HgcLeftGridView.GridControl.DataSource = BaseImpl.GetDataTableResult(searchSql);
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2019-02-01 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnSplitterPositionChanged(object sender, EventArgs e)
//            {
//                if (this.Model != null)
//                {
//                    IniUtil.WriteMenuConfig(string.Format("BaseWidth_{0}", this.Model.ModuleCode), this.scc_container.SplitterPosition + "");
//                }
//            }
//            /// <summary>
//            /// <para>说明：表格拖拽完成</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The e.</param>
//            void OnDragGridCompleted(object sender, GridDragGridArgs e)
//            {
//                if (e.GridViewSelectRows == null || e.GridViewSelectRows.Length == 0 || e.SelectGridRow == null) return;
//
//                try
//                {
//                    if (MessageUtil.Show(MessageKeys.DragSelectRow, MessageBoxButtons.YesNo) == DialogResult.Yes)
//                    {
//                        int[] records = new int[e.GridViewSelectRows.Length];
//                        string fieldName = this.LeftControlModelobj.FieldName;
//
//                        for (int i = 0; i < e.GridViewSelectRows.Length; i++)
//                        {
//                            DataRow item = e.GridViewSelectRows[i];
//                            try
//                            {
//                                records[i] = ReplaceUtil.ReplaceRowParamCond(item, this.ModuleModelObj.DragCond)
//                                        ? ModuleImpl.Instance.UpdateRecord(this.ModuleModelObj.TableName, fieldName, e.SelectGridRow[fieldName] + "", this.ModuleGridObj.ParmaryKey, item[this.ModuleGridObj.ParmaryKey] + "")
//                                        : 0;
//                            }
//                            catch (Exception ex)
//                            {
//                                LogUtil.Instance.WriteError(ex);
//                            }
//                        }
//                        int successCount = records.Contains(1) ? records.Where(x => x == 1).Count() : 0;
//                        int faultCount = records.Contains(0) ? records.Where(x => x == 0).Count() : 0;
//
//                        string tipMsg = string.Empty;
//                        if (successCount > 0) tipMsg = string.Format(MessageKeys.DragSuccessCount, successCount);
//                        if (faultCount > 0) tipMsg += successCount > 0 ? "\r\n" : "" + string.Format(MessageKeys.DragFaultCount, faultCount);
//
//                        MessageUtil.Show(tipMsg);
//                        this.SetMainGridView(string.Format(" and {0} like '{1}%' ", fieldName, e.SelectGridRow[fieldName]));
//                    }
//                }
//                catch (Exception ex)
//                {
//                    LogUtil.Instance.WriteError(ex);
//                    MessageUtil.Show(MessageKeys.OperFault);
//                }
//            }
//            /// <summary>
//            /// <para>说明：树拖拽完成</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-11-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The e.</param>
//            void OnDragTreeCompleted(object sender, GridDragTreeArgs e)
//            {
//                if (e.GridViewSelectRows == null || e.GridViewSelectRows.Length == 0 || e.SelectTreeNode == null) return;
//
//                if (MessageUtil.Show(string.Format(MessageKeys.DragSelectNode, e.SelectTreeNode.Text), MessageBoxButtons.YesNo) == DialogResult.Yes)
//                {
//                    int[] records = new int[e.GridViewSelectRows.Length];
//                    string fieldName = this.LeftControlModelobj.FieldName;
//
//                    for (int i = 0; i < e.GridViewSelectRows.Length; i++)
//                    {
//                        DataRow item = e.GridViewSelectRows[i];
//                        try
//                        {
//                            records[i] = ReplaceUtil.ReplaceRowParamCond(item, this.ModuleModelObj.DragCond)
//                                    ? ModuleImpl.Instance.UpdateRecord(this.ModuleModelObj.TableName, fieldName, e.SelectTreeNode.Name, this.ModuleGridObj.ParmaryKey, item[this.ModuleGridObj.ParmaryKey] + "")
//                                    : 0;
//                        }
//                        catch (Exception ex)
//                        {
//                            LogUtil.Instance.WriteError(ex);
//                        }
//                    }
//
//                    int successCount = records.Contains(1) ? records.Where(x => x == 1).Count() : 0;
//                    int faultCount = records.Contains(0) ? records.Where(x => x == 0).Count() : 0;
//
//                    string tipMsg = string.Empty;
//                    if (successCount > 0) tipMsg = string.Format(MessageKeys.DragSuccessCount, successCount);
//                    if (faultCount > 0) tipMsg += successCount > 0 ? "\r\n" : "" + string.Format(MessageKeys.DragFaultCount, faultCount);
//
//                    MessageUtil.Show(tipMsg);
//
//                    this.SetMainGridView(string.Format(" and {0} = '{1}' ", fieldName, this.HtvLeftTreeView.GetSelectValue()));
//                }
//            }
//            /// <summary>
//            /// <para>说明：树节点点击后刷新数据</para>
//            /// <para>创建人：</para>
//            /// <para>创建日期：2018-10-26 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="TreeViewEventArgs"/> instance containing the event data.</param>
//            void OnTreeNodeClick(object sender, TreeViewEventArgs e)
//            {
//                string where = string.Format(" and {0} like '{1}%' ", this.LeftControlModelobj.FieldName, e.Node.Name);
//                this.SetMainGridView(where);
//
//                if (this.OnLeftTreeViewRowClickCallBack != null)
//                    this.OnLeftTreeViewRowClickCallBack(this.HtvLeftTreeView, e);
//            }
//        }
//    }
//
//    using System;
//    using System.Collections.Generic;
//    using System.ComponentModel;
//    using System.Data;
//    using System.Drawing;
//    using System.Linq;
//    using System.Text;
//    using System.Windows.Forms;
//    using Ant.Control;
//    using Ant.Control.Model;
//    using Ant.Business.Impl;
//    using Ant.Data;
//    using DevExpress.XtraGrid.Views.Grid;
//    using Ant.Model;
//    using DevExpress.XtraGrid.Columns;
//    using DevExpress.XtraGrid.Views.Base;
//    using System.Data.SqlClient;
//    using System.Threading;
//    using Lskj.Control.Model;
//    using Ant.Util;
//    using Ant.Business;
//    using Ant.Core;
//
//
//    namespace Ant.Tool
//    {
//        public partial class FrmModuleMain : Form
//        {
//        #region 基础配置方法相关
//        public FrmModuleMain()
//            {
//                InitializeComponent();
//
//                this.Base_Main_MenuEx.GridView.RowCellClick += new RowCellClickEventHandler(OnTopGridViewRowCellClicked);
//                this.Base_Main_ModuleEx.GridView.RowCellClick += new RowCellClickEventHandler(OnDetailGridViewRowCellClick);
//                this.Base_Menu_DetailEx.GridView.RowCellClick += new RowCellClickEventHandler(OnGridViewRowCellClick);
//                this.Base_Menu_AuditStepEx.GridView.RowCellClick += new RowCellClickEventHandler(OnGridViewAuditRowCellClick);
//                this.Base_Menu_AuditFlowEx.GridView.RowCellClick += new RowCellClickEventHandler(OnGridViewAufitRowClick);
//                this.Base_Menu_FieldEx.GridView.RowCellClick += new RowCellClickEventHandler(OnGridViewBaseMenuFieldRowCellClick);
//                this.Base_Menu_FieldEx.OnCreateCondEvent += new EventHandler(OnBaseMenuFieldExCreateCondEvent);
//                this.auditAttachEx1.OnFocusedRowChanged += new FocusedRowChangedEventHandler(OnAuditAttachExOnFocusedRowChanged);
//            }
//            /// <summary>
//            /// <para>说明：加载左侧菜单</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            private void InitSystemMenus()
//            {
//                DataTable table = BaseModuleImpl.Instance.GetTreeMenus();
//
//                this.tvLeft.TreeNodeKeyField = "dm";
//                this.tvLeft.TreeNodeTextField = "mc";
//                this.tvLeft.BindTreeView(table);
//                this.tvLeft.TreeNodeSelectAfter += new TreeViewEventHandler(OnTreeNodeSelectAfter);
//            }
//            /// <summary>
//            /// <para>说明：初始基础配置</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            private void InitModulePage(DataRow rowItem)
//            {
//                if (rowItem == null) return;
//
//                string formKey = rowItem["formKey"] + "";
//                string dllCoid = rowItem["dllCoid"] + "";
//                string caption = rowItem["ToolsName"] + "";
//                string condKey = rowItem["condKey"] + "";
//
//                this.Base_Lable_MenuName.Text = "当前操作模块->" + caption;
//                this.Base_Menu_FieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridFields(formKey);
//                this.Base_Menu_FieldEx.TypeCode = dllCoid;
//                this.Base_Menu_ColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(dllCoid);
//                this.Base_Menu_RightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(dllCoid);
//                this.Base_Menu_AssistEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridAssists(dllCoid);
//                this.Base_Menu_CondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(condKey);
//            }
//            /// <summary>
//            /// <para>说明：初始化基础明细数据</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="rowItem">The row item.</param>
//            private void InitDetailPage(DataRow rowItem)
//            {
//                string formKey = rowItem["formKey"] + "";
//                string caption = rowItem["ToolsName"] + "";
//
//                this.Base_Lable_DetailName.Text = "当前操作模块->" + caption;
//                this.Base_Menu_DetailEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetail(formKey);
//            }
//            /// <summary>
//            /// <para>说明：初始化基础审核数据</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="rowItem">The row item.</param>
//            private void InitAuditPage(DataRow rowItem)
//            {
//                string typeCode = rowItem["dllCoid"] + "";
//                string caption = rowItem["ToolsName"] + "";
//                string overBackKey = rowItem["OverBackKey"] + "";
//                string OverBackSql = rowItem["OverBackSql"] + "";
//                string OverBackOper = rowItem["OverBackOper"] + "";
//
//                this.MeOverOper.Text = OverBackOper;
//                this.MeOverSql.Text = OverBackSql;
//                this.Base_Lable_AuditName.Text = "当前操作模块->" + caption;
//                this.Base_Menu_AuditStepEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAudit(typeCode);
//                this.Base_Menu_AuditOverCondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(overBackKey);
//            }
//            /// <summary>
//            /// <para>说明：初始化权限设置</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="rowItem">The row item.</param>
//            private void InitPrevPage(DataRow rowItem)
//            {
//
//            }
//        #endregion
//
//        #region 基础配置事件相关
//            /// <summary>
//            /// <para>说明：窗口加载</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnLoad(object sender, EventArgs e)
//            {
//                try
//                {
//                    WaitForm.ShowForm();
//                    Application.DoEvents();
//
//                    this.Tag = this.Text = this.Text + "[" + SystemInfo.Instance.ClientName + "]";
//                    this.InitSystemMenus();
//                    this.btnSearch.PerformClick();
//                    this.txtSearch.Focus();
//                }
//                catch (Exception ex)
//                {
//                    MessageUtil.Show(ex);
//                }
//                finally
//                {
//                    WaitForm.HideForm();
//                }
//            }
//            /// <summary>
//            /// <para>说明：加载功能模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="TreeViewEventArgs"/> instance containing the event data.</param>
//            void OnTreeNodeSelectAfter(object sender, TreeViewEventArgs e)
//            {
//                TreeNode node =  this.tvLeft.TreeView.SelectedNode;
//                if (node != null && node.Tag is DataRow)
//                {
//                    DataRow rowItem = node.Tag as DataRow;
//                    this.Base_Main_MenuEx.GridControl.DataSource = BaseModuleImpl.Instance.GetSystemMenuChilds(rowItem["MenuId"] + "");
//                }
//            }
//            /// <summary>
//            /// <para>说明：查询数据</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnSearchClick(object sender, EventArgs e)
//            {
//                string searchValue = this.txtSearch.Text.Trim();
//
//                string whereCond = string.Format(" and (DllCoid like '%{0}%' OR ToolsName like '%{0}%' OR dbo.P_getpy(ToolsName) like '%{0}%')", searchValue);
//                this.Base_Main_ModuleEx.GridControl.DataSource = BaseModuleImpl.Instance.GetSystemMenus("", whereCond);
//            }
//            /// <summary>
//            /// <para>说明：添加菜单</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnAddClick(object sender, EventArgs e)
//            {
//                TreeNode node = this.tvLeft.TreeView.SelectedNode;
//
//                if (node != null)
//                {
//                    DataRow leftRow = this.tvLeft.TreeView.SelectedNode.Tag as DataRow;
//                    if (this.tvLeft.TreeView.SelectedNode.Nodes.Count > 0)
//                    {
//                        MessageUtil.Show(ResourceKeys.SelectedNodeContainNode);
//                    }
//                    else
//                    {
//                        string subSysId = leftRow["SubSysId"] + "";
//                        string menuStruct = leftRow["MenuStruct"] + "";
//                        string parentId = leftRow["MenuId"] + "";
//
//                        this.Base_Main_MenuEx.AddNewRow(parentId, menuStruct, subSysId);
//                    }
//                }
//                else
//                {
//                    MessageUtil.Show(ResourceKeys.SelectedNodeIsNull);
//                }
//            }
//            /// <summary>
//            /// <para>说明：删除菜单</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDeleteClick(object sender, EventArgs e)
//            {
//                if (this.Base_Main_MenuEx.DeleteRow())
//                {
//                    this.OnTreeNodeSelectAfter(null, null);
//                }
//            }
//            /// <summary>
//            /// <para>说明：保存菜单</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnSaveClick(object sender, EventArgs e)
//            {
//                if (this.Base_Main_MenuEx.Save())
//                {
//                    this.OnTreeNodeSelectAfter(null, null);
//                }
//            }
//            /// <summary>
//            /// <para>说明：增加模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailAddClick(object sender, EventArgs e)
//            {
//                this.Base_Main_ModuleEx.AddNewRow();
//            }
//            /// <summary>
//            /// <para>说明：删除基础档案配置</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailDeleteClick(object sender, EventArgs e)
//            {
//                if (this.Base_Main_ModuleEx.Delete())
//                {
//                    this.btnSearch.PerformClick();
//                }
//            }
//            /// <summary>
//            /// <para>说明：保存模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailSaveClick(object sender, EventArgs e)
//            {
//                if (this.Base_Main_ModuleEx.Save())
//                {
//                    if (!string.IsNullOrEmpty(txtSearch.Text))
//                    {
//                        this.btnSearch.PerformClick();
//                    }
//                    else
//                    {
//                        this.OnTopGridViewRowCellClicked(null, null);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：导入模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailImportClick(object sender, EventArgs e)
//            {
//
//            }
//            /// <summary>
//            /// <para>说明：导出模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailExportClick(object sender, EventArgs e)
//            {
//
//            }
//            /// <summary>
//            /// <para>说明：复制模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnDetailCopyClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string dllId = rowItem["DllId"] + "";
//                    string sourceMenuCode = rowItem["DllCoid"] + "";
//
//                    if (string.IsNullOrEmpty(dllId))
//                    {
//                        MessageUtil.Show("请先保存数据,在复制.");
//                        return;
//                    }
//
//                    FrmCopy frmCopy = new FrmCopy();
//                    frmCopy.ShowDialog();
//
//                    if (!string.IsNullOrEmpty(frmCopy.MenuCode))
//                    {
//                        string menuCode = frmCopy.MenuCode;
//
//                        SqlParameter pMsg = new SqlParameter("@msg", SqlDbType.VarChar, 2000);
//                        pMsg.Direction = ParameterDirection.Output;
//                        SqlParameter returnValue = new SqlParameter("@return", SqlDbType.Int, 4);
//                        returnValue.Direction = ParameterDirection.ReturnValue;
//                        SqlParameter[] param =
//                                {
//                                        new SqlParameter("@modid",SqlDbType.VarChar,20),
//                                        new SqlParameter("@toModid",SqlDbType.VarChar,20),
//                                        pMsg,
//                                        returnValue
//                                };
//                        param[0].Value = sourceMenuCode;
//                        param[1].Value = menuCode;
//                        DataSet ds = SqlHelper.ExecuteDataSet(CommandType.StoredProcedure, "CopyModuleConfig", "CopyModuleConfig", param);
//                        string rMsg = pMsg.Value.ToString();
//                        int result = Convert.ToInt32(returnValue.Value + "");
//                        if (result > 0)
//                        {
//                            MessageUtil.Show(ResourceKeys.OperSuccess);
//                        }
//                        else
//                        {
//                            MessageUtil.Show(rMsg);
//                        }
//                    }
//                }
//                else
//                {
//                    MessageUtil.Show("请选择一行复制.");
//                }
//            }
//            /// <summary>
//            /// <para>说明：生成前缀记录</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-07-09 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnTsmiPrefixClick(object sender, EventArgs e)
//            {
//                // 判断有没有重名前缀
//                // 添加/提示
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tableName = rowItem["SQLDT1"] + "";
//                    string colPrefix = rowItem["sourcebillID"] + "";
//
//                    if (string.IsNullOrEmpty(tableName))
//                    {
//                        MessageUtil.Show("请先填写主表名.");
//                        return;
//                    }
//                    if (string.IsNullOrEmpty(colPrefix))
//                    {
//                        MessageUtil.Show("请填写前缀。");
//                        return;
//                    }
//                    string msg;
//                    if (BaseImpl.CreateTablePrefix(tableName, colPrefix, out msg))
//                    {
//                        MessageUtil.Show(ResourceKeys.OperSuccess);
//                    }
//                    else
//                    {
//                        MessageUtil.Show(msg);
//                    }
//                }
//                else
//                {
//                    MessageUtil.Show(ResourceKeys.SelectRowIsNull);
//                }
//            }
//            /// <summary>
//            /// <para>说明：管理字段</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnManagerClick(object sender, EventArgs e)
//            {
//
//            }
//            /// <summary>
//            /// <para>说明：加载绑定模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-10 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="DevExpress.XtraGrid.Views.Base.FocusedRowChangedEventArgs"/> instance containing the event data.</param>
//            void OnTopGridViewRowCellClicked(object sender, RowCellClickEventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_MenuEx.GridView.GetFocusedDataRow();
//
//                if (rowItem != null)
//                {
//                    string menuCode = rowItem["UrlParams"] + "";
//                    this.Base_Main_ModuleEx.GridControl.DataSource = BaseModuleImpl.Instance.GetSystemMenus(menuCode);
//                }
//            }
//            /// <summary>
//            /// <para>说明：生成审批必要字段</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-21 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnTsmiCreateAndutFieldClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem == null)
//                {
//                    MessageUtil.Show(ResourceKeys.SelectRowIsNull);
//                }
//                else
//                {
//                    string tableName = rowItem["SQLDT1"] + "";
//                    string perfix = rowItem["sourcebillID"] + "";
//
//                    if (string.IsNullOrEmpty(tableName))
//                    {
//                        MessageUtil.Show("请先填写表名.");
//                        return;
//                    }
//
//                    try
//                    {
//                        BaseImpl.CreateAuditField(tableName, perfix);
//                        MessageUtil.Show(ResourceKeys.OperSuccess);
//                    }
//                    catch (Exception ex)
//                    {
//                        MessageUtil.Show(ex);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：焦点行选中</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="DevExpress.XtraGrid.Views.Base.FocusedRowChangedEventArgs"/> instance containing the event data.</param>
//            void OnDetailGridViewRowCellClick(object sender, RowCellClickEventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    this.Text = this.Tag + "-->[" + rowItem["ToolsName"] + "]";
//                }
//            }
//            /// <summary>
//            /// <para>说明：搜索基础模块</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="PreviewKeyDownEventArgs"/> instance containing the event data.</param>
//            void OnSearchPreviewKeyDown(object sender, PreviewKeyDownEventArgs e)
//            {
//                if (e.KeyCode == Keys.Enter)
//                    this.btnSearch.PerformClick();
//            }
//            /// <summary>
//            /// <para>说明：切换Tab标签</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-11 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="DevExpress.XtraTab.TabPageChangedEventArgs"/> instance containing the event data.</param>
//            void OnTabSelectedPageChanging(object sender, DevExpress.XtraTab.TabPageChangingEventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (e.Page == XtpModule)
//                        this.InitModulePage(rowItem);
//                    else if (e.Page == XtpDetail)
//                        this.InitDetailPage(rowItem);
//                    else if (e.Page == XtpAudit)
//                        this.InitAuditPage(rowItem);
//                    else
//                        this.InitPrevPage(rowItem);
//                }
//                else
//                {
//                    MessageUtil.Show("请先选择模块.");
//                    e.Cancel = true;
//                }
//            }
//        #endregion
//
//
//
//        #region 模块字段事件相关
//            /// <summary>
//            /// <para>说明：模块字段行选中</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="FocusedColumnChangedEventArgs"/> instance containing the event data.</param>
//            void OnGridViewBaseMenuFieldRowCellClick(object sender, RowCellClickEventArgs e)
//            {
//                DataRow moduleItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (moduleItem != null && rowItem != null && "3".Equals(rowItem["fieldsqlTag"] + "") && "2".Equals(moduleItem["dllType"] + ""))
//                {
//                    // 显示左侧配置
//                    this.PL_FieldEx.Visible = true;
//                    this.Xtp_FieldLeftColor.PageVisible = true;
//                    this.Xtp_FieldLeftCond.PageVisible = true;
//                    this.Xtp_FieldLeftRight.PageVisible = true;
//
//                    string fieldKey = rowItem["fieldKey"] + "";
//
//                    this.Base_Menu_FieldLeftEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseModuleLeftGridFields(fieldKey);
//                    this.Base_Menu_FieldLeftColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(fieldKey);
//                    this.Base_Menu_FieldLeftRightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(fieldKey);
//                    this.Base_Menu_FieldLeftCondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(fieldKey);
//                }
//                else
//                {
//                    this.PL_FieldEx.Visible = false;
//                    this.Xtp_FieldLeftColor.PageVisible = false;
//                    this.Xtp_FieldLeftCond.PageVisible = false;
//                    this.Xtp_FieldLeftRight.PageVisible = false;
//                }
//            }
//            /// <summary>
//            /// <para>说明：添加字段</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tab = rowItem["DllCoid"] + "";
//                    string formKey = rowItem["formKey"] + "";
//
//                    this.Base_Menu_FieldEx.AddNewRow(tab, formKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldCreateClick(object sender, EventArgs e)
//            {
//                try
//                {
//                    WaitForm.ShowForm(ResourceKeys.CreateColumnsing);
//
//                    DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                    if (rowItem != null)
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        string tab = rowItem["DllCoid"] + "";
//                        string sql = rowItem["SQL"] + "";
//                        string tableName = rowItem["SQLDT1"] + "";
//
//                        if (this.Base_Menu_FieldEx.CreateFields(tab, formKey, sql, tableName))
//                        {
//                            this.Base_Menu_FieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridFields(rowItem["formKey"] + "");
//                        }
//                    }
//                }
//                catch (Exception)
//                {
//                }
//                finally
//                {
//                    WaitForm.HideForm();
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldEx.DeleSelectedRows())
//                    {
//                        this.Base_Menu_FieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridFields(rowItem["formKey"] + "");
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldEx.SaveFields())
//                    {
//                        this.Base_Menu_FieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridFields(rowItem["formKey"] + "");
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldDefineClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    try
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        string selectSql = AESUtil.Encrypt(string.Format(@"select b.orderid,b.id as fieldid,fieldname,username1 as username,fieldsqltag as fieldtypeid,defaultdate as defaultValue from  p_systemDlltab a left join p_systemwordbooktab  b on a.DllCoid=b.tab where a.formkey='{0}' and fieldsqltag<>3 and username1 is not null and isnull(addVisible,0)=0", formKey));
//                        string updateSql = AESUtil.Encrypt(string.Format(@"update p_systemwordbooktab set controlleft=b.controlleft ,controlTop=b.controlTop, controlHeight=b.controlHeight,controlWidth=b.controlWidth, tabOrder=b.tabOrder from  p_systemControlLocation b where p_systemwordbooktab.formKey=b.formKey and p_systemwordbooktab.id=b.fieldId and b.formkey='{0}'", formKey));
//                        string connectStr = AESUtil.Encrypt(DBConfig.Instance.GetConnection());
//
//                        FormHelper.StartProcess(PubUtil.DesingerPath, new string[] { formKey, selectSql, updateSql, connectStr });
//                    }
//                    catch (Exception ex)
//                    {
//                        MessageUtil.Show(ex);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldButtClick(object sender, EventArgs e)
//            {
//                this.Base_Menu_FieldEx.Stff();
//            }
//            /// <summary>
//            /// <para>说明：生成查询条件</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-07-09 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuFieldExCreateCondEvent(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                DataRow rowFieldItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null && rowFieldItem != null)
//                {
//                    string condKey = rowItem["condKey"] + "";
//                    string id = rowItem["DllID"] + "";
//
//                    string fieldName = rowFieldItem["fieldName"] + "_cond";
//                    string controlLabel = rowFieldItem["username1"] + "";
//                    string controlType = rowFieldItem["fieldsqlTag"] + "";
//                    string sourceSql = rowFieldItem["fieldsql"] + "";
//                    string keyField = rowFieldItem["fieldsqlid"] + "";
//                    string resultField = rowFieldItem["fieldsqlname"] + "";
//                    string checkCond = " and " + rowFieldItem["fieldName"] + " like '%{value}%'";
//
//                    if (this.Base_Menu_CondEx.AddNewRow(id, condKey, fieldName, controlLabel, controlType, sourceSql, keyField, resultField, checkCond))
//                    {
//                        this.Base_Menu_CondEx.Save();
//                        this.Base_Menu_CondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(condKey);
//                    }
//                }
//                else
//                {
//                    MessageUtil.Show(ResourceKeys.SelectRowIsNull);
//                }
//            }
//        #endregion
//
//        #region 模块颜色事件相关
//            /// <summary>
//            /// <para>说明：颜色添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuColorAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tab = rowItem["DllCoid"] + "";
//
//                    this.Base_Menu_ColorEx.AddNewRow(tab);
//                }
//            }
//            /// <summary>
//            /// <para>说明：颜色删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuColorDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_ColorEx.Delete())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_ColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(tab);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：颜色保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuColorSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_ColorEx.Save())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_ColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(tab);
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块右键菜单事件相关
//            /// <summary>
//            /// <para>说明：右键菜单添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuRightAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tab = rowItem["DllCoid"] + "";
//                    this.Base_Menu_RightEx.AddNewRow(tab, "0");
//                }
//            }
//            /// <summary>
//            /// <para>说明：右键菜单删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuRightDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_RightEx.Delete())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_RightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(tab);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：右键菜单保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuRightSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_RightEx.Save())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_RightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(tab);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：添加常用右键菜单</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-07-06 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuRightCommonClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string menuCode = rowItem["DllCoid"] + "";
//                    string primaryKey = BaseModuleImpl.GetBasePrimaryKey(menuCode);
//
//                    this.Base_Menu_RightEx.AddCommonRow(menuCode, "单据提交", "p_baseApply {@typeCode,@billDocumentId,@operatorId,@operatorName,@comfirmType,@msg}",
//                            1, "确认提交单据吗？", "提交成功", "提交失败", "'{Affirmer}'='0' or '{Affirmer}'=''", menuCode, "{" + primaryKey + "}", "{loginid}", "{loginname}", "1");
//
//                    this.Base_Menu_RightEx.AddCommonRow(menuCode, "取消提交", "p_baseApply {@typeCode,@billDocumentId,@operatorId,@operatorName,@comfirmType,@msg}",
//                            1, "确认取消提交单据吗？", "取消成功", "取消失败", "''{Affirmer}'<>'0' and '{Ban}'<>'1'", menuCode, "{" + primaryKey + "}", "{loginid}", "{loginname}", "2");
//
//                    this.BaseMenuRightSave.PerformClick();
//                }
//            }
//        #endregion
//
//        #region 模块辅助功能事件相关
//            /// <summary>
//            /// <para>说明：辅助功能添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuAssistAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tab = rowItem["DllCoid"] + "";
//                    this.Base_Menu_AssistEx.AddNewRow(tab, "1");
//                }
//            }
//            /// <summary>
//            /// <para>说明：辅助功能删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuAssistDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_AssistEx.Delete())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_AssistEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridAssists(tab);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：辅助功能保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuAssistSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_AssistEx.Save())
//                    {
//                        string tab = rowItem["DllCoid"] + "";
//                        this.Base_Menu_AssistEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridAssists(tab);
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块条件事件相关
//            /// <summary>
//            /// <para>说明：条件添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuCondAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string condKey = rowItem["condKey"] + "";
//                    string id = rowItem["DllID"] + "";
//
//                    this.Base_Menu_CondEx.AddNewRow(id, condKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：条件删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuCondDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_CondEx.Delete())
//                    {
//                        string formKey = rowItem["condKey"] + "";
//                        this.Base_Menu_CondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：条件保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-12 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            private void OnBaseMenuCondSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_CondEx.Save())
//                    {
//                        string formKey = rowItem["condKey"] + "";
//                        this.Base_Menu_CondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：设计</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-21 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuCondDesingerClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    try
//                    {
//                        string formKey = rowItem["condKey"] + "";
//                        string selectSql = AESUtil.Encrypt(string.Format(@"select b.orderid,b.id as fieldid,b.controlname as fieldname,b.controllabel as username,b.controltype as fieldtypeid,b.defaultvalue
//                        from p_systembillsourcecond b
//                        where b.formkey = '{0}'  order by orderid", formKey));
//                        string updateSql = AESUtil.Encrypt(string.Format(@"update p_systembillsourcecond set controlleft=b.controlleft ,controlTop=b.controlTop, controlHeight=b.controlHeight,controlWidth=b.controlWidth from  p_systemControlLocation b where p_systembillsourcecond.formKey=b.formKey and p_systembillsourcecond.id=b.fieldId and b.formKey='{0}'", formKey));
//                        string connectStr = AESUtil.Encrypt(DBConfig.Instance.GetConnection());
//
//                        FormHelper.StartProcess(PubUtil.DesingerPath, new string[] { formKey, selectSql, updateSql, connectStr });
//                    }
//                    catch (Exception ex)
//                    {
//                        MessageUtil.Show(ex);
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块字段左侧配置相关
//            /// <summary>
//            /// <para>说明：表格左侧字段</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBtnBaseButtomCreateClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null && "3".Equals(rowItem["fieldsqlTag"] + ""))
//                {
//                    string fieldSql = rowItem["fieldSql"] + "";
//                    if (!string.IsNullOrEmpty(fieldSql))
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        string fieldid = rowItem["id"] + "";
//
//                        this.Base_Menu_FieldLeftEx.CreateFields(fieldid, fieldKey, fieldSql);
//                        this.Base_Menu_FieldLeftEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseModuleLeftGridFields(fieldKey);
//                    }
//                    else
//                    {
//                        MessageUtil.Show("请先配置左侧SQL");
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：表格左侧字段删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBtnBaseBottomDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftEx.DeleSelectedRows())
//                    {
//                        this.Base_Menu_FieldLeftEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseModuleLeftGridFields(rowItem["fieldKey"] + "");
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：表格左侧字段保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBtnBaseBottomSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftEx.SaveFields())
//                    {
//                        this.Base_Menu_FieldLeftEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseModuleLeftGridFields(rowItem["fieldKey"] + "");
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块字段左侧颜色相关
//            /// <summary>
//            /// <para>说明：模块字段左侧颜色添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftColorAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string fieldKey = rowItem["fieldKey"] + "";
//
//                    this.Base_Menu_FieldLeftColorEx.AddNewRow(fieldKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧颜色删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftColorDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftColorEx.Delete())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(fieldKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧颜色保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftColorSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftColorEx.Save())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(fieldKey);
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块字段左侧右键相关
//            /// <summary>
//            /// <para>说明：模块字段左侧右键添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftRightAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string fieldKey = rowItem["fieldKey"] + "";
//                    this.Base_Menu_FieldLeftRightEx.AddNewRow(fieldKey, "0");
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧右键删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftRightDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftRightEx.Delete())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftRightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(fieldKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧右键保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuLeftRightSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftRightEx.Save())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftRightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(fieldKey);
//                    }
//                }
//            }
//        #endregion
//
//        #region 模块字段左侧条件相关
//            /// <summary>
//            /// <para>说明：模块字段左侧条件添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void BaseMenuLeftCondAdd_Click(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string condKey = rowItem["fieldKey"] + "";
//                    string id = rowItem["id"] + "";
//
//                    this.Base_Menu_FieldLeftCondEx.AddNewRow(id, condKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧条件</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void BaseMenuLeftCondDelete_Click(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftCondEx.Delete())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftCondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(fieldKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：模块字段左侧条件</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void BaseMenuLeftCondSave_Click(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_FieldEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_FieldLeftCondEx.Save())
//                    {
//                        string fieldKey = rowItem["fieldKey"] + "";
//                        this.Base_Menu_FieldLeftCondEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridCond(fieldKey);
//                    }
//                }
//            }
//        #endregion
//
//
//
//        #region 明细列表事件相关
//            /// <summary>
//            /// <para>说明：明细列表添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string tab = rowItem["DllCoid"] + "";
//                    string formKey = rowItem["formKey"] + "";
//                    this.Base_Menu_DetailEx.AddNewRow(tab, formKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailEx.Delete())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetail(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailEx.Save())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetail(formKey);
//                    }
//                }
//            }
//        #endregion
//
//        #region 明细列表字段事件相关
//            /// <summary>
//            /// <para>说明：明细列表字段添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailFieldAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string id = rowItem["id"] + "";
//                    string formKey = rowItem["formKey"] + "";
//
//                    this.Base_Menu_DetailFieldEx.AddNewRow(id, formKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表字段生成</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailFieldCreateClick(object sender, EventArgs e)
//            {
//                try
//                {
//                    WaitForm.ShowForm(ResourceKeys.CreateColumnsing);
//
//                    DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                    if (rowItem != null)
//                    {
//                        string id = rowItem["id"] + "";
//                        string detailKey = rowItem["formKey"] + "";
//                        string detailSQL = rowItem["detailSQL"] + "";
//
//                        this.Base_Menu_DetailFieldEx.CreateFields(id, detailKey, detailSQL);
//                        this.Base_Menu_DetailFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetailFields(detailKey);
//                    }
//                }
//                catch (Exception ex)
//                {
//                    MessageUtil.Show(ex);
//                }
//                finally
//                {
//                    WaitForm.HideForm();
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表字段删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailFieldDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailFieldEx.DeleSelectedRows())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetailFields(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表字段保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailFieldSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailFieldEx.SaveFields())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseDetailFields(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表字段对照</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The source of the event.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailFieldSuttClick(object sender, EventArgs e)
//            {
//                this.Base_Menu_DetailFieldEx.Stff();
//            }
//        #endregion
//
//        #region 明细列表颜色事件相关
//            /// <summary>
//            /// <para>说明：明细列表颜色添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailColorAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string formKey = rowItem["formKey"] + "";
//
//                    this.Base_Menu_DetailColorEx.AddNewRow(formKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表颜色删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailColorDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailColorEx.Delete())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表颜色保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailColorSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailColorEx.Save())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors(formKey);
//                    }
//                }
//            }
//        #endregion
//
//        #region 明细列表右键菜单事件相关
//            /// <summary>
//            /// <para>说明：明细列表右键菜单添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailRightAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string formKey = rowItem["formKey"] + "";
//                    this.Base_Menu_DetailRightEx.AddNewRow(formKey, "0");
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表右键菜单删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailRightDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailRightEx.Delete())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailRightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表右键菜单保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailRightSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailRightEx.Save())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailRightEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridRights(formKey);
//                    }
//                }
//            }
//        #endregion
//
//        #region 明细列表图表事件相关
//            /// <summary>
//            /// <para>说明：明细列表图表添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailChartAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string formKey = rowItem["formKey"] + "";
//                    this.Base_Menu_DetailChartEx.AddNewRow(formKey);
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表图表删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailChartDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailChartEx.Delete())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailChartEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridChart(formKey);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：明细列表图表保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuDetailChartSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_DetailEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_DetailChartEx.Save())
//                    {
//                        string formKey = rowItem["formKey"] + "";
//                        this.Base_Menu_DetailChartEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridChart(formKey);
//                    }
//                }
//            }
//        #endregion
//
//
//
//        #region 审核步骤事件相关
//            /// <summary>
//            /// <para>说明：审核步骤选中行</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="FocusedRowChangedEventArgs"/> instance containing the event data.</param>
//            void OnGridViewAuditRowCellClick(object sender, RowCellClickEventArgs e)
//            {
//                DataRow moduleItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//
//                if (rowItem != null)
//                {
//                    this.Base_Lable_AuditName.Text = "当前操作模块->" + moduleItem["ToolsName"] + "->" + rowItem["stepName"] + "";
//                }
//            }
//            /// <summary>
//            /// <para>说明：Tab标签切换</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="DevExpress.XtraTab.TabPageChangedEventArgs"/> instance containing the event data.</param>
//            void OnXtcAuditTabSelectedPageChanged(object sender, DevExpress.XtraTab.TabPageChangedEventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//
//                string typeCode = rowItem != null ? rowItem["typeCode"] + "" : string.Empty;
//                string stepCode = rowItem != null ? rowItem["stepCode"] + "" : string.Empty;
//                string id = rowItem != null ? rowItem["id"] + "" : string.Empty;
//
//                if (e.Page == XtpAuditField) // 加载审核步骤字段
//                    this.Base_Menu_AuditStepFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAuditFlowStepGridField(typeCode, stepCode);
//                else if (e.Page == XtpAuditFlow) // 加载小类设置
//                    this.Base_Menu_AuditFlowEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAuditFlows(typeCode);
//                else if (e.Page == XtpAuditColor) // 加载审核步骤颜色
//                    this.Base_Menu_AuditStepColorEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseGridColors("FLOWSTEP_" + typeCode + "_" + stepCode + "_" + id);
//                else if (e.Page == XtpAuditOver) // 加载终审设置
//                    this.Base_Menu_AuditOverEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAuditFlowStepGridField(typeCode, "999");
//                else if (e.Page == XtpAuditAttach) // 加载审核附加信息
//                    this.auditAttachEx1.InitializeAttach(this.Base_Main_ModuleEx.GridView.GetFocusedDataRow(), rowItem);
//            }
//            /// <summary>
//            /// <para>说明：显示小类名称</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="FocusedRowChangedEventArgs"/> instance containing the event data.</param>
//            void OnAuditAttachExOnFocusedRowChanged(object sender, FocusedRowChangedEventArgs e)
//            {
//                GridView view = sender as GridView;
//                if (view != null)
//                {
//                    DataRow rowItem = view.GetFocusedDataRow();
//
//                    this.labelControl1.Text = "当前操作列表->" + (rowItem != null ? rowItem["attachName"] + "" : "");
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string typeCode = rowItem["dllCoid"] + "";
//                    this.Base_Menu_AuditStepEx.AddNewRow(typeCode);
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_AuditStepEx.Delete())
//                    {
//                        string typeCode = rowItem["dllCoid"] + "";
//                        this.Base_Menu_AuditStepEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAudit(typeCode);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤保存</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditSaveClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Main_ModuleEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_AuditStepEx.Save())
//                    {
//                        string typeCode = rowItem["dllCoid"] + "";
//                        this.Base_Menu_AuditStepEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAudit(typeCode);
//                    }
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤生成SQL语句</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-13 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditCreateClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    DialogResult result = MessageUtil.Show("确定生成步骤语句?\r\n提示：生成会覆盖原有步骤语句.", MessageBoxButtons.YesNo);
//                    if (result == System.Windows.Forms.DialogResult.Yes)
//                    {
//                        string id = rowItem["id"] + "";
//                        string typeCode = rowItem["typeCode"] + "";
//                        string stepCode = rowItem["stepCode"] + "";
//
//                        string primaryKey = BaseImpl.GetResult(string.Format("select top 1 fieldname from p_systemwordbooktab where tab='{0}' order by orderid", typeCode)) + "";
//                        string masterTable = BaseImpl.GetResult(string.Format(@"select SQLDT1 from p_systemdlltab where dllCoid='{0}'", typeCode)) + "";
//
//                        if (!string.IsNullOrEmpty(masterTable))
//                        {
//                            string prefix = BaseImpl.GetColumnPrefix(masterTable);
//                            // 获取该表配置的列，根据列生成sql语句
//                            string sqlValue = string.Format(@"select fieldName,case when isnull(userName1,'')='' and isnull(sysname,'')='' then fieldName  when isnull(username1,'')='' then sysName  else username1 end as userName,
//                            fieldsqlTag fieldTypeId,isnull(fieldsqlid,'') lookupKeyField,isnull(fieldsqlname,'') lookupResult,isnull(fieldsql,'') lookupSql
//                            from p_systemwordbooktab a
//                            inner join sys.columns b on object_id('{0}')=b.object_id and lower(a.fieldname)=lower(b.name)
//                            where tab='{1}'", masterTable, typeCode);
//                            DataTable table = BaseImpl.GetDataTableResult(sqlValue);
//
//                            StringBuilder sbCreateSql = new StringBuilder();
//                            StringBuilder sbFields = new StringBuilder();
//                            StringBuilder sbJoinSqls = new StringBuilder();
//
//                            bool isContainerStepCode = false;
//
//                            for (int i = 0; i < table.Rows.Count; i++)
//                            {
//                                DataRow item = table.Rows[i];
//
//                                // 生成查询字段
//                                int fieldTypeId = string.IsNullOrEmpty(item["fieldTypeId"] + "") ? 0 : Convert.ToInt32(item["fieldTypeId"] + "");
//                                string fieldName = item["fieldName"] + "";
//                                string lookUpKeyField = item["lookupKeyField"] + "";
//                                string lookUpResult = item["lookupResult"] + "";
//                                string lookUpSql = item["lookupSql"] + "";
//
//                                if (lookUpSql.Contains("#")) continue;
//
//                                if (!isContainerStepCode && fieldName.ToLower() == (prefix + "StepCode").ToLower())
//                                {
//                                    isContainerStepCode = true;
//                                }
//
//                                if (ControlType.IsLookUpControl(fieldTypeId))
//                                {
//                                    sbFields.AppendLine(string.Format("tb_{0}.{1} as {0}_Name,", fieldName, lookUpResult));
//                                    sbJoinSqls.AppendLine(string.Format("left join ({0}) tb_{1} on a.{1}=tb_{1}.{2}", lookUpSql, fieldName, lookUpKeyField));
//                                }
//
//                                if (i == table.Rows.Count - 1)
//                                    sbFields.Append("a." + fieldName);
//                                else
//                                    sbFields.AppendLine("a." + fieldName + ",");
//                            }
//
//                            string first = "select ";
//                            string second = sbFields.ToString()+ (isContainerStepCode ? "" : string.Format(",{0} " + prefix + "StepCode", stepCode));
//                            string third = " from " + masterTable + " a \r\n";
//                            string four = sbJoinSqls.ToString();
//                            string five = string.Format(" where a.{0}stepcode={1} and isnull(a.{0}stepOver,0)=0 and exists(select 1 from p_baseflowoper where keyvalue=a.{2} and (charindex({3},{4}+browsers+{4})>0  or charindex({3},{4}+operators+{4})>0))", prefix, stepCode, primaryKey, "',{loginname},'", "','");
//
//                            BaseImpl.ExecSqlValue("update p_systemdlltabflow set stepSql = @stepSql where id=@id",
//                                    new SqlParameter[]
//                                            {
//                                                    new SqlParameter("stepSql",first+second+third+four+five),
//                                                    new SqlParameter("id",id)
//                                            });
//                            this.Base_Menu_AuditStepEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAudit(typeCode);
//                            MessageUtil.Show(ResourceKeys.OperSuccess);
//                        }
//                        else
//                        {
//                            MessageUtil.Show("该模块未配置表名.");
//                        }
//                    }
//                }
//                else
//                {
//                    MessageUtil.Show(ResourceKeys.SelectRowIsNull);
//                }
//            }
//        #endregion
//
//        #region 审核步骤字段信息事件相关
//            /// <summary>
//            /// <para>说明：审核步骤字段添加</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditFieldAddClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    string typeCode = rowItem["typeCode"] + "";
//                    string stepCode = rowItem["stepCode"] + "";
//                    this.Base_Menu_AuditStepFieldEx.AddNewRow(typeCode, stepCode);
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤字段生成</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditFieldCreateClick(object sender, EventArgs e)
//            {
//                try
//                {
//                    WaitForm.ShowForm(ResourceKeys.CreateColumnsing);
//
//                    DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//                    if (rowItem != null)
//                    {
//                        string typeCode = rowItem["typeCode"] + "";
//                        string stepCode = rowItem["stepCode"] + "";
//                        string stepSql = rowItem["stepSql"] + "";
//
//                        this.Base_Menu_AuditStepFieldEx.CreateFields(typeCode, stepCode, stepSql);
//                        this.Base_Menu_AuditStepFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAuditFlowStepGridField(typeCode, stepCode);
//                    }
//                }
//                catch (Exception)
//                {
//                }
//                finally
//                {
//                    WaitForm.HideForm();
//                }
//            }
//            /// <summary>
//            /// <para>说明：审核步骤字段删除</para>
//            /// <para>创建人：龚宇超</para>
//            /// <para>创建日期：2018-06-14 </para>
//            /// <para>修改人：</para>
//            /// <para>修改日期：</para>
//            /// <para>修改备注：</para>
//            /// <para>版本：1.0</para>
//            /// </summary>
//            /// <param name="sender">The sender.</param>
//            /// <param name="e">The <see cref="EventArgs"/> instance containing the event data.</param>
//            void OnBaseMenuAuditFieldDeleteClick(object sender, EventArgs e)
//            {
//                DataRow rowItem = this.Base_Menu_AuditStepEx.GridView.GetFocusedDataRow();
//                if (rowItem != null)
//                {
//                    if (this.Base_Menu_AuditStepFieldEx.Delete())
//                    {
//                        string typeCode = rowItem["typeCode"] + "";
//                        string stepCode = rowItem["stepCode"] + "";
//
//                        this.Base_Menu_AuditStepFieldEx.GridControl.DataSource = BaseModuleImpl.Instance.GetBaseAuditFlowStepGridField(typeCode, stepCode);
//                    }
//                }
//            }
//
//        }
//    }
}