package org.trinetix.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.trinetix.dao.EmployeeDao;
import org.trinetix.dao.EmployeeDaoImpl;
import org.trinetix.dto.Employee;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    private static final Log log = LogFactory.getLog(EmployeeController.class);

    @Autowired
    EmployeeDao dao;

    XStream xstream = new XStream(new DomDriver());

    @RequestMapping(value = "list")
    public String list(@RequestParam(value = "orderBy", required=false) String  orderBy, ModelMap model) throws SQLException {
        model.addAttribute("employeeList", dao.getList(orderBy));
        return "employeeList";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable String id, ModelMap model) throws SQLException {
        model.addAttribute("employee", dao.getById(id));
        model.addAttribute("managers", dao.getListByTitle("Manager"));
        model.addAttribute("titles", dao.getTitles());
        return "employeeEdit";
    }

    @RequestMapping(value = "save")
    public String save(@ModelAttribute("employeeAttribute") Employee employee, ModelMap model) throws SQLException {
        dao.save(employee);
        return list(null, model);
    }

    @RequestMapping(value = "saveXml")
    public String saveXml(@RequestParam(value = "employeeData") String  employeeData, ModelMap model) throws SQLException {
        Employee employee = (Employee) xstream.fromXML(employeeData);
        dao.save(employee);
        return list(null, model);
    }

    @RequestMapping(value = "editXml")
    public String editXml(ModelMap model) {
        return "employeeSaveXml";
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
       log.error("Request: " + req.getRequestURL() + " raised exception", exception);
        ModelAndView m= new ModelAndView();
        m.setViewName("error");
        return m;
    }
}
