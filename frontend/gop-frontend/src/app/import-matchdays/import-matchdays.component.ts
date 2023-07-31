import { Component } from '@angular/core';
import {ImportMatchdaysService} from "../import-matchdays.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-import-matchdays',
  templateUrl: './import-matchdays.component.html',
  styleUrls: ['./import-matchdays.component.css']
})
export class ImportMatchdaysComponent {

  seasonId: string | undefined;

  constructor(private importMatchdaysService: ImportMatchdaysService,
              private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.route.params.subscribe(params =>
      this.seasonId = params['seasonId']
    )
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.importMatchdaysService.importMatchdays(+this.seasonId!!,file).subscribe()
  }

}
