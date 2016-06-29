package cn.scau.edu.ssm.showdoc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>
{

	@Override
	public Date convert(String time)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			return df.parse(time);
		} catch (ParseException e) 
		{
			return null;
		}
	}

}
