import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;

import java.awt.event.*;


public class FileIO
	{
		public static void main(String argv[]) throws IOException
		{
			FileInputStream stream = new FileInputStream("colors.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			StreamTokenizer tokens = new StreamTokenizer(reader);

			String s;
			int n1, n2, n3;
			

			while(tokens.nextToken()!=tokens.TT_EOF)
			{
				s = (String) tokens.sval;
				tokens.nextToken();
				n1= (int) tokens.nval;
				tokens.nextToken();
				n2 = (int) tokens.nval;
				tokens.nextToken();
				n3 = (int) tokens.nval;

				System.out.println(s+ " " + n1 + " " + n2 + " " + n3);
			}
			stream.close();
		}

	}