using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;
using System.Windows.Media;

namespace ObservableCollectionDemo.ViewModels.Converters
{
	public class GenderToColorConverter: IValueConverter
	{
		public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
		{
			if (value != null)
			{
				if (value.ToString().Equals("male", StringComparison.InvariantCultureIgnoreCase))
				{
					return new SolidColorBrush(Colors.Green);
				}
				else if (value.ToString().Equals("female", StringComparison.InvariantCultureIgnoreCase))
				{
					return new SolidColorBrush(Colors.Pink);
				}
				else
				{
					return new SolidColorBrush(Colors.Purple);
				}
			}
			else
			{
				return null;
			}
		}

		public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
		{
			throw new NotImplementedException();
		}

	}
}
