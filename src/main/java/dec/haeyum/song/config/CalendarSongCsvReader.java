package dec.haeyum.song.config;

import dec.haeyum.song.dto.CalendarSongCsvDto;
import dec.haeyum.song.dto.SongDetailCsvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CalendarSongCsvReader {

    @Value("${song.calendar-song.csv-path}")
    private String calendarSongCsv;

    public FlatFileItemReader<CalendarSongCsvDto> csvScheduleReader() {

        //파일 경로 지정 및 인코딩
        FlatFileItemReader<CalendarSongCsvDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource(calendarSongCsv));
        flatFileItemReader.setEncoding("UTF-8");
        flatFileItemReader.setLinesToSkip(1);   // 첫 줄(헤더) 무시

        // CSV 데이터를 한 줄씩 읽어서 DTO에 매핑
        DefaultLineMapper<CalendarSongCsvDto> defaultLineMapper = new DefaultLineMapper<>();

        // 따로 설정하지 않으면 기본값은 ","
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        // 필드 설정
        delimitedLineTokenizer.setNames(CalendarSongCsvDto.getFieldNames().toArray(String[]::new));
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        // 매칭할 class 타입 지정 (필드 지정)
        BeanWrapperFieldSetMapper<CalendarSongCsvDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(CalendarSongCsvDto.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
