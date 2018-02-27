package fr.eni.clinique.ihm;

import java.util.List;

import javax.swing.JScrollPane;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
/**
 * 
 * @author egrapin2017
 *
 */
public class ApplyController {
		
	public static ApplyController instance;
	
	private login ecr;
	private int panel = 0;
	
	
	
	private ApplyController() throws DALException
	{	
		ecr = new login();}
	
	public static ApplyController getInstance() throws DALException
	{
		if( ApplyController.instance == null)
		{
			ApplyController.instance = new ApplyController();
		}
		return ApplyController.instance;
	}
	

}
