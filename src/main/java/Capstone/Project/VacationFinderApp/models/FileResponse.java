package Capstone.Project.VacationFinderApp.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse {
    String fileName;
    String fileDownloadUri;
    String fileType;
    Long size;
}
