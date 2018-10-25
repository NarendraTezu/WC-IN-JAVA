/*
   Wc program replica.
 */


import java.io.*;
import java.lang.*;
class Test
{
	int optionArrLength;
	int fileArrLength;
	int numOfFile;
	String []currentDirectory = null; //new String[numOfFile];
	String []options = new String[optionArrLength];
	String []files = new String[fileArrLength];
	public static void main(String []args)throws FileNotFoundException, IOException			//this is main method
	{
		String dir = System.getProperty("user.dir");
		Test t = new Test();
		t.listFiles(dir);
		t.argumentParser(args);
		t.valueInsert(args);
		t.countWc(t.options, t.files, t.currentDirectory);
	}

	// Finding ohe number of file and number of arguments and argument are valid or not.

	void argumentParser(String args[])throws FileNotFoundException, IOException
	{
		if(args.length < 1) 			// this check there any argument or not.
		{
			System.out.println("*********   Invlid Entry  **********");
			System.out.println("Try 'Test --help' for more information");
			System.exit(1);
		}
		int count = 0;			

		//this for loop for checking the duplicate arg and set there null if it is present already

		for(int i=0; i< args.length; i++)
		{
			for(int j = (i+1); j<args.length; j++)
			{
				if(args[i] != null && args[i].equals(args[j]))
				{	
					args[j] = null;
					count++;
				}
			}
		}
		String[] Array = new String[]{"-c","--bytes","-m","--chars","-l","--lines","-L","--max-line-length","-w","--words","--help", "*"};
		char firstLetter = '-';
		int optionArrLength = 0;
		//this for loop for counting the valid no of option and file
		for(int i = 0; i < args.length; i++)
		{
			int flag = 0;
			if(args[i]!=null && args[i].charAt(0) == firstLetter || args[i].charAt(0) == '*' )
			{
				for(int j = 0; j < 11; j++)
				{	
					if (args[i].equals(Array[j]))
					{
						optionArrLength++;
						flag = 1;
						break;
					}
				}
				if(flag == 0)
				{
					System.out.println("*****Invalid arg entry***** ");
					System.out.println("Try 'Test --help' for more information");
					System.exit(1);
				}
			}
		}
		int fileArrLength = (args.length-optionArrLength-count);	
		this.optionArrLength = optionArrLength;
		this.fileArrLength = fileArrLength;

	}


	// insert the file and option in array

	void valueInsert(String args[])
	{
		String[] optionarr = new String[optionArrLength];
		String[] filearr = new String[fileArrLength];

		String[] Array = new String[]{"-c","--bytes","-m","--chars","-l","--lines","-L","--max-line-length","-w","--words","--help", "*"};
		char firstLetter = '-';
		int posOption=0;
		int posFile=0;
		for(int i = 0; i < args.length; i++)
		{
			// comparing if argument is starting with -

			if(args[i]!=null && args[i].charAt(0) == firstLetter || args[i].charAt(0) == '*') 
			{
				optionarr[posOption] = args[i];
				posOption++;
			}	
			else
			{ 
				if(args[i]!=null)
				{
					filearr[posFile] = args[i];
					posFile++;
				}
			}
		}
		options = optionarr.clone();
		files = filearr.clone();
	}


	// this method for taking the n number of option and file and cout the number of charector in all file as well as sepratly.

	void charCount(String[] files)throws FileNotFoundException, IOException 
	{
		int ch, char_count = 0, numOfChar = 0;
/*		if(files.length < 1)
		{
			 for(int i =0; i< currentDirectory.length; i++)
                        {
                                FileInputStream g = new FileInputStream(currentDirectory[i]);
                                while((ch = g.read()) != -1)
                                {
                                        char_count++;
                                }
                                numOfChar = numOfChar + char_count;
                                System.out.println("Number of charector is : "+char_count + " in   " + currentDirectory[i]);
                                char_count = 0;
                        }

		
		}
		else
		{*/
			for(int i =0; i< fileArrLength; i++)
			{
				FileInputStream f = new FileInputStream(files[i]);
				while((ch = f.read()) != -1)
				{
					char_count++;
				}
				numOfChar = numOfChar + char_count;
				System.out.println("Number of charector is : "+char_count + " in   " + files[i]);
				char_count = 0;
			}
	//	}
		System.out.println("Total Number of char in all files is : "+numOfChar);
	}


	// this method for taking the n number of option and file and cout the number of words in all file as well as sepratly.

	void wordCount(String[] files)throws FileNotFoundException, IOException
	{
		int ch, word_count = 0, numOfword = 0;
		for(int i =0; i< fileArrLength; i++)
		{
			FileInputStream f = new FileInputStream(files[i]);
			while((ch = f.read()) != -1)
			{
				if(ch == ' ' || ch == '\n')
				{
					word_count++;
				}
			}
			numOfword = numOfword + word_count;
			System.out.println("Number of words is : "+word_count + " in   " + files[i]);
			word_count = 0;
		}
		System.out.println("Total Number of words in all files is : "+numOfword);
	}


	// this method for taking the n number of option and file and cout the number of line in all file as well as sepratly.

	void lineCount(String[] files)throws FileNotFoundException, IOException
	{
		int ch, line_count = 0, numOfline = 0, total_numOf_line = 0;
		for(int i =0; i< fileArrLength; i++)
		{
			FileInputStream f = new FileInputStream(files[i]);
			while((ch = f.read()) != -1)
			{
				if(ch == '\n')
				{
					line_count++;
				}
			}
			System.out.println("number of line : "+ line_count + " in file " + files[i]);
			numOfline = line_count;
			line_count = 0;
			total_numOf_line = total_numOf_line + numOfline;
		}
		System.out.println("total number of line : "+ total_numOf_line);
	}


	// this method for taking the n number of option and file and show the longest line in all file as well as sepratly.

	void longestLine(String[] files)throws FileNotFoundException, IOException
	{
		int ch, temp = 0, char_count = 0, long_line = 0;
		for(int i =0; i< fileArrLength; i++)
		{
			FileInputStream f = new FileInputStream(files[i]);
			while((ch = f.read()) != -1) 
			{
				if(ch != '\n')
				{
					char_count++;
				}else{

					if(long_line < char_count)
					{ 
						long_line = char_count;
						char_count=0;
					}

				}
			}
			System.out.println("longestLine : "+long_line+" in file "+ files[i]);
			if(temp < long_line)
			{
				temp = long_line;
				long_line = 0;
			}
		}
		System.out.println("longest line in all file is : " +temp);
	}


	// this method for taking the single argument as option and showing the help for valid option.

	void help()throws FileNotFoundException, IOException
	{
		BufferedReader ch = new BufferedReader(new FileReader("help.txt"));
		String line;
		for(int i=0; i< optionArrLength; i++)
		{
			while((line = ch.readLine()) != null)
			{
				System.out.println(line);
			}
			ch.close();
		}
	}


	// This method for list the file of current directory and store in a array.

	void listFiles(String path)
	{	
		int numOfFile = 0;
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File file : files)
		{
			if (file.isFile())
			{
				numOfFile++;
				//		System.out.println(file.getName());
			}
		}
		this.numOfFile = numOfFile;
		currentDirectory = new String[numOfFile];
		numOfFile = 0;
		for (File file : files)
		{
			if (file.isFile())
			{
				currentDirectory[numOfFile] = file.getName(); 
				numOfFile++;
			}
		}

		//		for( int i=0; i<this.numOfFile; i++)
		//			System.out.println(currentDirectory[i]);
	}



	// this method for taking the n number of option and file and preforming the task based on choice.

	void countWc(String[] options, String[] files, String [] currentDirectory)throws FileNotFoundException, IOException
	{


		//	for( int i=0; i<this.numOfFile; i++)
		//			System.out.println(currentDirectory[i]);
						
		if(options.length < 1 && files.length >= 1)          // this check there any argument or not.
		{
			charCount(files);
                        lineCount(files);
                        wordCount(files);

		}
		else
		{
			for(int j=0; j< optionArrLength; j++)
			{
				{
					String myString = options[j];
					switch (myString) {

						case "*":
							{
								charCount(currentDirectory);
								lineCount(currentDirectory);
								wordCount(currentDirectory);
								break;
							}


						case "-c":
							charCount(files);
							break;

						case "--bytes":
							charCount(files);
							break;

						case "-m":
							charCount(files);
							break;

						case "--chars":
							charCount(files);
							break;

						case "-l":
							lineCount(files);
							break;

						case "--lines":
							lineCount(files);
							break;

						case "-w":
							wordCount(files);
							break;

						case "--words":
							wordCount(files);
							break;

						case "-L":
							longestLine(files);
							break;

						case "--max-line-length":
							longestLine(files);
							break;

						case "--help":
							help();
							break;
					}
				}
			}
		}	
	}
}
