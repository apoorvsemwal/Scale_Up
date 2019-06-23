using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using TestCommands.ViewModel.Commands;

namespace TestCommands.ViewModel
{
	public class MessageViewModel
	{
		public MessageCommand DisplayMessageCommand { get; private set; }

		public RelayCommand MessageBoxCommand { get; private set; }
		public RelayCommand ConsoleLogCommand { get; private set; }

		public ObservableCollection<string> myMessages { get; private set; }

		public MessageViewModel()
		{
			DisplayMessageCommand = new MessageCommand(DisplayMessage); //Setting Function for the Delegate

			myMessages = new ObservableCollection<string>()
			{
				"Hello",
				"Testing Relay Commands",
				"I am a Message Box",
				"I am a Console"
			};

			MessageBoxCommand = new RelayCommand(DisplayMessage, MessageBoxCanUse);
			ConsoleLogCommand = new RelayCommand(DisplayMessageConsole, ConsoleLogCanUse);
		}

		//public string MessageText { get; set; } Not required when we are using command parameter

		public void DisplayMessage(Object message)
		{
			MessageBox.Show(message as string);
		}

		public void DisplayMessageConsole(Object message)
		{
			Console.WriteLine(message as string);
		}

		public bool MessageBoxCanUse(object message)
		{
			if ((string)message == "I am a Console")
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		public bool ConsoleLogCanUse(object message)
		{
			if ((string)message == "I am a Message Box")
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
}
